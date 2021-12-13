package com.github.rafaelsantos.brewer.controller.handler;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.github.rafaelsantos.brewer.service.exception.TypeNameExistsException;

@ControllerAdvice
public class ControllerAdviceExceptionHandler {

	@ExceptionHandler(TypeNameExistsException.class)
	public ResponseEntity<String> handleTypeNameExistsException(TypeNameExistsException error) {
		return ResponseEntity.badRequest().body(error.getMessage());
	}
}
