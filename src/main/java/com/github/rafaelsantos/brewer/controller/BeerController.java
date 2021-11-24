package com.github.rafaelsantos.brewer.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.github.rafaelsantos.brewer.model.Beer;

@Controller
public class BeerController {

	@RequestMapping("/beer/add")
	public String add() {
		return "beer/add";
	}
	
	@RequestMapping(value = "/beer/add", method = RequestMethod.POST)
	public String save(@Valid Beer beer, BindingResult result) {
		if (result.hasErrors())
			System.out.println("Error");
		
		System.out.println("POST " + beer.getSku()); 
		return "beer/add";
	}
}