package com.github.rafaelsantos.brewer.storage.local;

import static java.nio.file.FileSystems.getDefault;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.github.rafaelsantos.brewer.storage.ImageStorage;

public class ImageStorageLocal implements ImageStorage {
	
	private static final Logger logger = LoggerFactory.getLogger(ImageStorageLocal.class);
	
	private Path local;
	private Path temporary;
	
	public ImageStorageLocal() {
		this(getDefault().getPath(System.getenv("HOME"), ".brewer"));
	}
	
	public ImageStorageLocal(Path path) {
		this.local = path;
		createPaths();
	}

	@Override
	public String saveTemporary(MultipartFile[] files) {
		if (files != null && files.length > 0) {
			MultipartFile file = files[0];
			String name = generateFileNamePrefix(file.getOriginalFilename());
			String path = String.format("%s%s%s", this.temporary.toAbsolutePath(), getDefault().getSeparator(), name);
			
			try {
				file.transferTo(new File(path));
			} catch (IOException error) {
				throw new RuntimeException("Error when saving temporary image", error);
			}
			
			return name;
		}
		
		return null;
	}
	
	private void createPaths() {
		try {
			Files.createDirectories(this.local);
			this.temporary = getDefault().getPath(this.local.toString(), "temporary");
			Files.createDirectories(this.temporary);
			
			if (logger.isDebugEnabled()) {
				String defaultPath = String.format("Default path: %s", this.local.toAbsolutePath());
				String temporaryPath = String.format("Temporary path: %s", this.temporary.toAbsolutePath());
				
				logger.debug("Image paths created successfully");
				logger.debug(defaultPath);
				logger.debug(temporaryPath);
			}
		} catch (IOException error) {
			throw new RuntimeException("Error when creating path to save image", error);
		}
	}
	
	private String generateFileNamePrefix(String filename) {
		return String.format("%s_%s", UUID.randomUUID().toString(), filename);
	}
}
