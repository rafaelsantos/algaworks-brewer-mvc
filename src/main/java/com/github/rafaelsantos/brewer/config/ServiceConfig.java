package com.github.rafaelsantos.brewer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.github.rafaelsantos.brewer.service.BeerService;
import com.github.rafaelsantos.brewer.storage.ImageStorage;
import com.github.rafaelsantos.brewer.storage.local.ImageStorageLocal;

@Configuration
@ComponentScan(basePackageClasses = BeerService.class)
public class ServiceConfig {
	
	@Bean
	public ImageStorage imageStorage() {
		return new ImageStorageLocal();
	}
}
