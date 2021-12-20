package com.github.rafaelsantos.brewer.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/images")
public class ImageController {

	@PostMapping
	public String upload(@RequestParam("files[]") MultipartFile[] files) {
		return "ok";
	}
}
