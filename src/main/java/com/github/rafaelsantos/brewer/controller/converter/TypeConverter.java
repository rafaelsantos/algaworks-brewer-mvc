package com.github.rafaelsantos.brewer.controller.converter;

import org.springframework.core.convert.converter.Converter;

import com.github.rafaelsantos.brewer.model.Type;

public class TypeConverter implements Converter<String, Type> {

	@Override
	public Type convert(String code) {
		Type type = new Type();
		type.setCode(Long.valueOf(code));
		
		return type;
	}
}
