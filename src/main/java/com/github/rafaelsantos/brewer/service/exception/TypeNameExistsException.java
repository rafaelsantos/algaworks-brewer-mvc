package com.github.rafaelsantos.brewer.service.exception;

public class TypeNameExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public TypeNameExistsException(String message) {
		super(message);
	}
}
