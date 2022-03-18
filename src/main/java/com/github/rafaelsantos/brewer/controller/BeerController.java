package com.github.rafaelsantos.brewer.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.github.rafaelsantos.brewer.controller.page.PageWrapper;
import com.github.rafaelsantos.brewer.model.Beer;
import com.github.rafaelsantos.brewer.model.Flavor;
import com.github.rafaelsantos.brewer.model.Origin;

import com.github.rafaelsantos.brewer.repository.BeerRepository;
import com.github.rafaelsantos.brewer.repository.TypeRepository;
import com.github.rafaelsantos.brewer.repository.filter.BeerFilter;

import com.github.rafaelsantos.brewer.service.BeerService;

@Controller
@RequestMapping("/beer")
public class BeerController {
	
	@Autowired
	private TypeRepository typeRepository;
	
	@Autowired
	private BeerService beerService;
	
	@Autowired
	private BeerRepository beerRepository;

	@RequestMapping("/add")
	public ModelAndView add(Beer beer) {
		ModelAndView modelView = new ModelAndView("beer/add");
		
		modelView.addObject("flavours", Flavor.values());
		modelView.addObject("types", typeRepository.findAll());
		modelView.addObject("origins", Origin.values());
		
		return modelView;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView save(@Valid Beer beer, BindingResult result, Model model, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return add(beer);
		}
		
		beerService.save(beer);
		attributes.addFlashAttribute("message", "Beer saved successfully!");
		
		return new ModelAndView("redirect:/beer/add");
	}
	
	@GetMapping
	public ModelAndView search(BeerFilter beerFilter, BindingResult result, @PageableDefault(size = 2) Pageable pageable, HttpServletRequest request) {
		ModelAndView modelView = new ModelAndView("beer/search");
		
		modelView.addObject("types", typeRepository.findAll());
		modelView.addObject("flavours", Flavor.values());
		modelView.addObject("origins", Origin.values());
		
		PageWrapper<Beer> page = new PageWrapper<>(beerRepository.filter(beerFilter, pageable), request);
		modelView.addObject("page", page);
		
		return modelView;
	}
}