package com.github.rafaelsantos.brewer.storage;

import org.springframework.web.multipart.MultipartFile;

public interface ImageStorage {

	public void saveTemporary(MultipartFile[] files);
}
