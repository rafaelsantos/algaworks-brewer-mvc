package com.github.rafaelsantos.brewer.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "beer")
public class Beer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long code;

	@NotBlank(message = "SKU is required")
	private String sku;
	
	@NotBlank(message = "Name is required")
	private String name;
	
	@Size(max = 50, message = "Text length must be between 1 and 50")
	private String description;
	
	private BigDecimal value;
	
	@Column(name = "alcohol_percentage")
	private BigDecimal alcoholPercentage;
	
	private BigDecimal commission;
	
	@Column(name = "stock_quantity")
	private Integer stockQuantity;
	
	@Enumerated(EnumType.STRING)
	private Origin origin;
	
	@Enumerated(EnumType.STRING)
	private Flavor flavor;
	
	@ManyToOne
	@JoinColumn(name = "type_code")
	private Type type;
	
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public BigDecimal getAlcoholPercentage() {
		return alcoholPercentage;
	}

	public void setAlcoholPercentage(BigDecimal alcoholPercentage) {
		this.alcoholPercentage = alcoholPercentage;
	}

	public BigDecimal getCommission() {
		return commission;
	}

	public void setCommission(BigDecimal commission) {
		this.commission = commission;
	}

	public Integer getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(Integer stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	public Origin getOrigin() {
		return origin;
	}

	public void setOrigin(Origin origin) {
		this.origin = origin;
	}

	public Flavor getFlavor() {
		return flavor;
	}

	public void setFlavor(Flavor flavor) {
		this.flavor = flavor;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		
		return result;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object)
			return true;
		
		if (object == null)
			return false;
		
		if (getClass() != object.getClass())
			return false;
		
		Beer other = (Beer) object;
		
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		
		return true;
	}
}
