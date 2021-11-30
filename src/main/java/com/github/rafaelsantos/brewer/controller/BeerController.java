package com.github.rafaelsantos.brewer.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.github.rafaelsantos.brewer.model.Beer;

@Controller
public class BeerController {

	@RequestMapping("/beer/add")
	public String add(Beer beer) {
		return "beer/add";
	}
	
	@RequestMapping(value = "/beer/add", method = RequestMethod.POST)
	public String save(@Valid Beer beer, BindingResult result, Model model, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return add(beer);
		}
		
		attributes.addFlashAttribute("message", "Data saved successfully!");
		return "redirect:/beer/add";
	}
	
	@RequestMapping("/beer/product")
	public String product() {
		return "beer/product";
	}
	
}