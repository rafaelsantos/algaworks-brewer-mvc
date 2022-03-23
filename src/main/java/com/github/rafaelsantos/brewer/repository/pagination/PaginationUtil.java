package com.github.rafaelsantos.brewer.repository.pagination;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import org.springframework.stereotype.Component;

@Component
public class PaginationUtil {
    
    public void prepare(Criteria criteria, Pageable pageable) {
        int currentPage = pageable.getPageNumber();
		int totalResultsPerPage = pageable.getPageSize();
		int firstResult = currentPage * totalResultsPerPage;
		
		criteria.setFirstResult(firstResult);
		criteria.setMaxResults(totalResultsPerPage);

        Sort sort = pageable.getSort();
        
		if (sort != null) {
			Sort.Order order = sort.iterator().next();
			String field = order.getProperty();

			criteria.addOrder(order.isAscending() ? Order.asc(field) : Order.desc(field));
		}
    }
}
