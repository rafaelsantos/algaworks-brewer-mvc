package com.github.rafaelsantos.brewer.storage;

import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;

public class ImageStorageRunnable implements Runnable {
	
	private MultipartFile[] files;
	private DeferredResult<String> result;
	
	public ImageStorageRunnable(MultipartFile[] files, DeferredResult<String> result) {
		this.files = files;
		this.result = result;
	}


	@Override
	public void run() {
		result.setResult("Image uploaded successfully");
	}

}
