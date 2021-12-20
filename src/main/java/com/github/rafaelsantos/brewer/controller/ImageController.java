package com.github.rafaelsantos.brewer.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;

import com.github.rafaelsantos.brewer.storage.ImageStorageRunnable;

@RestController
@RequestMapping("/images")
public class ImageController {

	@PostMapping
	public DeferredResult<String> upload(@RequestParam("files[]") MultipartFile[] files) {
		DeferredResult<String> result = new DeferredResult<>();
		
		Thread thread = new Thread(new ImageStorageRunnable(files, result));
		thread.start();
		
		return result;
	}
}
