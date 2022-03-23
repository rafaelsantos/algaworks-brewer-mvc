package com.github.rafaelsantos.brewer.repository.helper.type;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.github.rafaelsantos.brewer.model.Type;
import com.github.rafaelsantos.brewer.repository.filter.TypeFilter;
import com.github.rafaelsantos.brewer.repository.pagination.PaginationUtil;

import org.hibernate.Criteria;
import org.hibernate.Session;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

public class TypeRepositoryImpl implements TypeQueries {

    @PersistenceContext
	private EntityManager manager;

	@Autowired
	private PaginationUtil pagination;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
    public Page<Type> filter(TypeFilter filter, Pageable pageable) {
        Criteria criteria = manager.unwrap(Session.class).createCriteria(Type.class);

		pagination.prepare(criteria, pageable);
		addFilter(filter, criteria);
		
		return new PageImpl<>(criteria.list(), pageable, total(filter));
    }

    private Long total(TypeFilter filter) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Type.class);
		addFilter(filter, criteria);
        
		criteria.setProjection(Projections.rowCount());

		return (Long) criteria.uniqueResult();
	}

    private void addFilter(TypeFilter filter, Criteria criteria) {
		if (filter != null && !StringUtils.isEmpty(filter.getName()))
            criteria.add(Restrictions.ilike("name", filter.getName(), MatchMode.ANYWHERE));
	}
}
