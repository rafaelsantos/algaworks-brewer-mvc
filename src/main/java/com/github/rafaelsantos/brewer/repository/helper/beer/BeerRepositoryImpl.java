package com.github.rafaelsantos.brewer.repository.helper.beer;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

import com.github.rafaelsantos.brewer.model.Beer;
import com.github.rafaelsantos.brewer.repository.filter.BeerFilter;
import com.github.rafaelsantos.brewer.repository.pagination.PaginationUtil;

public class BeerRepositoryImpl implements BeerQueries {
	
	@PersistenceContext
	private EntityManager manager;

	@Autowired
	private PaginationUtil pagination;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public Page<Beer> filter(BeerFilter filter, Pageable pageable) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Beer.class);
		
		pagination.prepare(criteria, pageable);
		addFilter(filter, criteria);
		
		return new PageImpl<>(criteria.list(), pageable, total(filter));
	}
	
	private Long total(BeerFilter filter) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Beer.class);
		addFilter(filter, criteria);
		
		criteria.setProjection(Projections.rowCount());
		
		return (Long) criteria.uniqueResult();
	}

	private void addFilter(BeerFilter filter, Criteria criteria) {
		if (filter != null) {
			if (!StringUtils.isEmpty(filter.getSku()))
				criteria.add(Restrictions.eq("sku", filter.getSku()));
			
			if (!StringUtils.isEmpty(filter.getName()))
				criteria.add(Restrictions.ilike("name", filter.getName(), MatchMode.ANYWHERE));

			if (isTypeAvailable(filter))
				criteria.add(Restrictions.eq("type", filter.getType()));

			if (filter.getFlavor() != null)
				criteria.add(Restrictions.eq("flavor", filter.getFlavor()));

			if (filter.getOrigin() != null)
				criteria.add(Restrictions.eq("origin", filter.getOrigin()));

			if (filter.getMinimumPrice() != null)
				criteria.add(Restrictions.ge("value", filter.getMinimumPrice()));

			if (filter.getMaximumPrice() != null)
				criteria.add(Restrictions.le("value", filter.getMaximumPrice()));
		}
	}

	private boolean isTypeAvailable(BeerFilter filter) {
		return filter.getType() != null && filter.getType().getCode() != null;
	}
}
