package com.dotsub.lucas.jefile.web.rest;

import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class MetadataDto {
	private String name;
	private Optional<String> description;
	
	public MetadataDto(String name, Optional<String> description) {
		this.name = name;
		this.description = description;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description.orElse("");
	}
	
	public void setDescription(Optional<String> description) {
		this.description = description;
	}
}
