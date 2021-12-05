package com.github.rafaelsantos.brewer.model;

public enum Flavor {
	
	SWEETENED("Sweetened"),
	BITTER("Bitter"),
	STRONG("Strong"),
	FRUITY("Fruity"),
	SOFT("Soft");
	
	private String description;
	
	Flavor(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
