package com.github.rafaelsantos.brewer.controller.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import com.github.rafaelsantos.brewer.model.Type;

public class TypeConverter implements Converter<String, Type> {

	@Override
	public Type convert(String code) {
		if (!StringUtils.isEmpty(code)) {
			Type type = new Type();
			type.setCode(Long.valueOf(code));
			
			return type;
		}
		
		return null;
	}
}
