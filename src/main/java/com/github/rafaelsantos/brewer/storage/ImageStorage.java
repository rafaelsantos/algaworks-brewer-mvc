package com.github.rafaelsantos.brewer.storage;

import org.springframework.web.multipart.MultipartFile;

public interface ImageStorage {

	public String saveTemporary(MultipartFile[] files);

	public byte[] fetchTemporaryImage(String filename);

	public void save(String image);

	public byte[] fetchImage(String filename);
}
