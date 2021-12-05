package com.github.rafaelsantos.brewer.model;

public enum Origin {
	
	NATIONAL("National"),
	INTERNATIONAL("International");
	
	private String description;
	
	public String getDescription() {
		return description;
	}

	Origin(String description) {
		this.description = description;
	}
}
