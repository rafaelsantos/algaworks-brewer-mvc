package com.github.rafaelsantos.brewer.dto;

public class ImageDTO {

	private String name;
	private String contentType;
	
	public ImageDTO(String name, String contentType) {
		super();
		this.name = name;
		this.contentType = contentType;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getContentType() {
		return contentType;
	}
	
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
}
