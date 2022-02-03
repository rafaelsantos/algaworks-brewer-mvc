package com.github.rafaelsantos.brewer.repository.filter;

import java.math.BigDecimal;

import com.github.rafaelsantos.brewer.model.Flavor;
import com.github.rafaelsantos.brewer.model.Origin;
import com.github.rafaelsantos.brewer.model.Type;

public class BeerFilter {
	
	private String sku;
	private String name;
	
	private Type type;
	private Flavor flavor;
	
	private BigDecimal minimumPrice;
	private BigDecimal maximumPrice;
	
	private Origin origin;
	
	public String getSku() {
		return sku;
	}
	
	public void setSku(String sku) {
		this.sku = sku;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Type getType() {
		return type;
	}
	
	public void setType(Type type) {
		this.type = type;
	}
	
	public Flavor getFlavor() {
		return flavor;
	}
	
	public void setFlavor(Flavor flavor) {
		this.flavor = flavor;
	}
	
	public BigDecimal getMinimumPrice() {
		return minimumPrice;
	}
	
	public void setMinimumPrice(BigDecimal minimumPrice) {
		this.minimumPrice = minimumPrice;
	}
	
	public BigDecimal getMaximumPrice() {
		return maximumPrice;
	}
	
	public void setMaximumPrice(BigDecimal maximumPrice) {
		this.maximumPrice = maximumPrice;
	}

	public Origin getOrigin() {
		return origin;
	}

	public void setOrigin(Origin origin) {
		this.origin = origin;
	}
}
