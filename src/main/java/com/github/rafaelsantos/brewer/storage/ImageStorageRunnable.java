package com.github.rafaelsantos.brewer.storage;

import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;

import com.github.rafaelsantos.brewer.dto.ImageDTO;

public class ImageStorageRunnable implements Runnable {
	
	private MultipartFile[] files;
	private DeferredResult<ImageDTO> result;
	private ImageStorage imageStorage;
	
	public ImageStorageRunnable(MultipartFile[] files, DeferredResult<ImageDTO> result, ImageStorage imageStorage) {
		this.files = files;
		this.result = result;
		this.imageStorage = imageStorage;
	}


	@Override
	public void run() {
		String imageName = this.imageStorage.saveTemporary(files);
		String contentType = files[0].getContentType();
		
		result.setResult(new ImageDTO(imageName, contentType));
	}
}
