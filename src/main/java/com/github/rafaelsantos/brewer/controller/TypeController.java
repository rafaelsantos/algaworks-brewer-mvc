package com.github.rafaelsantos.brewer.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.github.rafaelsantos.brewer.controller.page.PageWrapper;
import com.github.rafaelsantos.brewer.model.Type;
import com.github.rafaelsantos.brewer.repository.TypeRepository;
import com.github.rafaelsantos.brewer.repository.filter.TypeFilter;
import com.github.rafaelsantos.brewer.service.TypeService;
import com.github.rafaelsantos.brewer.service.exception.TypeNameExistsException;

@Controller
@RequestMapping("/type")
public class TypeController {
	
	@Autowired
	private TypeService typeService;

	@Autowired
	private TypeRepository typeRepository;
	
	@RequestMapping("/add")
	public ModelAndView add(Type type) {
		return new ModelAndView("type/add");
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView save(@Valid Type type, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return add(type);
		}
		
		try {
			typeService.save(type);
		} catch(TypeNameExistsException error) {
			result.rejectValue("name", error.getMessage(), error.getMessage());
			return add(type);
		}

		attributes.addFlashAttribute("message", "Type saved sucessfully!");
		
		return new ModelAndView("redirect:/type/add");
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseEntity<?> save(@RequestBody @Valid Type type, BindingResult result) {
		if (result.hasErrors())
			return ResponseEntity.badRequest().body(result.getFieldError().getDefaultMessage());
		
		type = typeService.save(type);

		return ResponseEntity.ok(type);
	}

	@GetMapping
	public ModelAndView search(TypeFilter typeFilter, BindingResult result, @PageableDefault(size = 2) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView modelView = new ModelAndView("type/search");
		
		PageWrapper<Type> page = new PageWrapper<>(typeRepository.filter(typeFilter, pageable), httpServletRequest);
		modelView.addObject("page", page);
		
		return modelView;
	}
}
