package com.github.rafaelsantos.brewer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BeerController {

	@RequestMapping("/beer/add")
	public String add() {
		return "beer/add";
	}
	
	@RequestMapping(value = "/beer/add", method = RequestMethod.POST)
	public String save(String sku) {
		System.out.println("POST " + sku); 
		return "beer/add";
	}
}