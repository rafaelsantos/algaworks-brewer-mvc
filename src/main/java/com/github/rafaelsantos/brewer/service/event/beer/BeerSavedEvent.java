package com.github.rafaelsantos.brewer.service.event.beer;

import org.springframework.util.StringUtils;

import com.github.rafaelsantos.brewer.model.Beer;

public class BeerSavedEvent {

	private Beer beer;

	public BeerSavedEvent(Beer beer) {
		this.beer = beer;
	}

	public Beer getBeer() {
		return beer;
	}
	
	public boolean isImage() {
		return !StringUtils.isEmpty(beer.getImage());
	}
}
