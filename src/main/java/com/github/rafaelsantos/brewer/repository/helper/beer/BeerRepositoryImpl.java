package com.github.rafaelsantos.brewer.repository.helper.beer;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.github.rafaelsantos.brewer.model.Beer;
import com.github.rafaelsantos.brewer.repository.filter.BeerFilter;

public class BeerRepositoryImpl implements BeerQueries {
	
	@PersistenceContext
	private EntityManager manager;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<Beer> filter(BeerFilter filter) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Beer.class);
		
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
		
		return criteria.list();
	}
	
	private boolean isTypeAvailable(BeerFilter filter) {
		return filter.getType() != null && filter.getType().getCode() != null;
	}
}
