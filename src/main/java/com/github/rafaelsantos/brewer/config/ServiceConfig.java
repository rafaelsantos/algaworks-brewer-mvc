package com.github.rafaelsantos.brewer.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.github.rafaelsantos.brewer.service.BeerService;

@Configuration
@ComponentScan(basePackageClasses = BeerService.class)
public class ServiceConfig {

}
