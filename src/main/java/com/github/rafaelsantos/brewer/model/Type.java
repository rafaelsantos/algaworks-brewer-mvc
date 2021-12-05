package com.github.rafaelsantos.brewer.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "beer_type")
public class Type implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long code;
	
	private String name;
	
	@OneToMany(mappedBy = "type")
	private Collection<Beer> beers;

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
		
		Type other = (Type) object;
		
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		
		return true;
	}
}
