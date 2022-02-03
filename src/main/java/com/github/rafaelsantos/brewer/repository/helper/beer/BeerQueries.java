package com.github.rafaelsantos.brewer.repository.helper.beer;

import java.util.List;

import com.github.rafaelsantos.brewer.model.Beer;
import com.github.rafaelsantos.brewer.repository.filter.BeerFilter;

public interface BeerQueries {
	public List<Beer> filter(BeerFilter filter);
}
