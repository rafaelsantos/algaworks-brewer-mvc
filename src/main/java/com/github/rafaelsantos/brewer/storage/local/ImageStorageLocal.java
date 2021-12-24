package com.github.rafaelsantos.brewer.storage.local;

import static java.nio.file.FileSystems.getDefault;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

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

	@Override
	public void saveTemporary(MultipartFile[] files) {
		System.out.println("Saving temporary image");
	}
}
