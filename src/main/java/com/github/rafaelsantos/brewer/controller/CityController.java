package com.github.rafaelsantos.brewer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CityController {

	@RequestMapping("/city/add")
	public String add() {
		return "city/add";
	}
}
