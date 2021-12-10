package com.github.rafaelsantos.brewer.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.github.rafaelsantos.brewer.model.Beer;
import com.github.rafaelsantos.brewer.model.Flavor;
import com.github.rafaelsantos.brewer.model.Origin;
import com.github.rafaelsantos.brewer.repository.TypeRepository;
import com.github.rafaelsantos.brewer.service.BeerService;

@Controller
public class BeerController {
	
	@Autowired
	private TypeRepository typeRepository;
	
	@Autowired
	private BeerService beerService;

	@RequestMapping("/beer/add")
	public ModelAndView add(Beer beer) {
		ModelAndView modelView = new ModelAndView("beer/add");
		
		modelView.addObject("flavours", Flavor.values());
		modelView.addObject("types", typeRepository.findAll());
		modelView.addObject("origins", Origin.values());
		
		return modelView;
	}
	
	@RequestMapping(value = "/beer/add", method = RequestMethod.POST)
	public ModelAndView save(@Valid Beer beer, BindingResult result, Model model, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return add(beer);
		}
		
		beerService.save(beer);
		attributes.addFlashAttribute("message", "Beer saved successfully!");
		
		return new ModelAndView("redirect:/beer/add");
	}
}