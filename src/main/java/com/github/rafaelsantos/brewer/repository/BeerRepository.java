package com.github.rafaelsantos.brewer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.rafaelsantos.brewer.model.Beer;
import com.github.rafaelsantos.brewer.repository.helper.beer.BeerQueries;

@Repository
public interface BeerRepository extends JpaRepository<Beer, Long>, BeerQueries {
	
	public Optional<Beer> findBySkuIgnoreCase(String sku);

}
