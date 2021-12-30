package com.github.rafaelsantos.brewer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;

import com.github.rafaelsantos.brewer.dto.ImageDTO;
import com.github.rafaelsantos.brewer.storage.ImageStorage;
import com.github.rafaelsantos.brewer.storage.ImageStorageRunnable;

@RestController
@RequestMapping("/images")
public class ImageController {
	
	@Autowired
	private ImageStorage imageStorage;

	@PostMapping
	public DeferredResult<ImageDTO> upload(@RequestParam("files[]") MultipartFile[] files) {
		DeferredResult<ImageDTO> result = new DeferredResult<>();
		
		Thread thread = new Thread(new ImageStorageRunnable(files, result, imageStorage));
		thread.start();
		
		return result;
	}
	
	@GetMapping("/temporary/{name:.*}")
	public byte[] fetchTemporaryImage(@PathVariable("name") String filename) {
		return imageStorage.fetchTemporaryImage(filename);
	}
}
