package com.github.rafaelsantos.brewer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
	
	@RequestMapping("/user/add")
	public String add() {
		return "user/add";
	}
}
