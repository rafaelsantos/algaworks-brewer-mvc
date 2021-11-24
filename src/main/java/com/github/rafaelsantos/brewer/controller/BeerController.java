package com.github.rafaelsantos.brewer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BeerController {

	@RequestMapping("/beer/add")
	public String add() {
		return "beer/add";
	}
}
