package com.github.rafaelsantos.brewer.service.event.beer;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class BeerListener {
	
	@EventListener(condition = "#event.image")
	public void beerSaved(BeerSavedEvent event) {
		System.out.println(String.format("Beer saved %s", event.getBeer().getName()));
	}

}
