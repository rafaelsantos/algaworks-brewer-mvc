package com.github.rafaelsantos.brewer.repository.helper.beer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.github.rafaelsantos.brewer.model.Beer;
import com.github.rafaelsantos.brewer.repository.filter.BeerFilter;

public interface BeerQueries {
	public Page<Beer> filter(BeerFilter filter, Pageable pageable);
}
