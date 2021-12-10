package com.github.rafaelsantos.brewer.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.rafaelsantos.brewer.model.Type;
import com.github.rafaelsantos.brewer.repository.TypeRepository;
import com.github.rafaelsantos.brewer.service.exception.TypeNameExistsException;

@Service
public class TypeService {

	@Autowired
	private TypeRepository typeRepository;
	
	@Transactional
	public void save(Type type) {
		Optional<Type> typeFound = typeRepository.findByNameIgnoreCase(type.getName());
		
		if (typeFound.isPresent())
			throw new TypeNameExistsException("Type name already exists");
		
		typeRepository.save(type);
	}
}
