package com.github.rafaelsantos.brewer.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.github.rafaelsantos.brewer.model.Type;
import com.github.rafaelsantos.brewer.service.TypeService;
import com.github.rafaelsantos.brewer.service.exception.TypeNameExistsException;

@Controller
public class TypeController {
	
	@Autowired
	private TypeService typeService;
	
	@RequestMapping("type/add")
	public ModelAndView add(Type type) {
		return new ModelAndView("type/add");
	}
	
	@RequestMapping(value = "/type/add", method = RequestMethod.POST)
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

}
