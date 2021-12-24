package com.github.rafaelsantos.brewer.storage;

import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;

import com.github.rafaelsantos.brewer.dto.ImageDTO;

public class ImageStorageRunnable implements Runnable {
	
	private MultipartFile[] files;
	private DeferredResult<ImageDTO> result;
	
	public ImageStorageRunnable(MultipartFile[] files, DeferredResult<ImageDTO> result) {
		this.files = files;
		this.result = result;
	}


	@Override
	public void run() {
		String imageName = files[0].getOriginalFilename();
		String contentType = files[0].getContentType();
		
		result.setResult(new ImageDTO(imageName, contentType));
	}

}
