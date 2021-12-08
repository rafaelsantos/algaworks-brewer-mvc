package com.github.rafaelsantos.brewer.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.github.rafaelsantos.brewer.model.Beer;
import com.github.rafaelsantos.brewer.repository.BeerRepository;

@Controller
public class BeerController {
	
	private static final Logger logger = LoggerFactory.getLogger(BeerController.class);
	
	@Autowired
	private BeerRepository beerRepository;

	@RequestMapping("/beer/add")
	public String add(Beer beer) {
		beerRepository.findAll();
		
		if (logger.isDebugEnabled())
			logger.debug(beer.toString());
		
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
}