package com.dotsub.lucas.jefile.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Metadata {
	private String name;
	private String description;
	private String createdAt;
	private String contentUrl;
	
	public Metadata() {
	}
	
	public Metadata(String name, String description, String contentUrl) {
		this.name = name;
		this.description = description;
		this.contentUrl = contentUrl;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	
	public String getContentUrl() {
		return contentUrl;
	}
	
	public void setContentUrl(String contentUrl) {
		this.contentUrl = contentUrl;
	}
}
