package com.github.rafaelsantos.brewer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.rafaelsantos.brewer.model.Beer;
import com.github.rafaelsantos.brewer.repository.BeerRepository;
import com.github.rafaelsantos.brewer.service.event.beer.BeerSavedEvent;

@Service
public class BeerService {

	@Autowired
	private BeerRepository beerRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Transactional
	public void save(Beer beer) {
		beerRepository.save(beer);
		publisher.publishEvent(new BeerSavedEvent(beer));
	}
}
