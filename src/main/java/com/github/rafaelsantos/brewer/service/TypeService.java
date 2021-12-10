package com.github.rafaelsantos.brewer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.rafaelsantos.brewer.model.Type;
import com.github.rafaelsantos.brewer.repository.TypeRepository;

@Service
public class TypeService {

	@Autowired
	private TypeRepository typeRepository;
	
	@Transactional
	public void save(Type type) {
		typeRepository.save(type);
	}
}
