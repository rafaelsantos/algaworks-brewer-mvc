package com.github.rafaelsantos.brewer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.rafaelsantos.brewer.model.Type;

@Repository
public interface TypeRepository extends JpaRepository<Type, Long> {
	
	public Optional<Type> findByNameIgnoreCase(String name);

}
