package com.github.rafaelsantos.brewer.service.event.beer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.github.rafaelsantos.brewer.storage.ImageStorage;

@Component
public class BeerListener {
	
	@Autowired
	private ImageStorage imageStorage;
	
	@EventListener(condition = "#event.image")
	public void beerSaved(BeerSavedEvent event) {
		imageStorage.save(event.getBeer().getImage());
	}
}
