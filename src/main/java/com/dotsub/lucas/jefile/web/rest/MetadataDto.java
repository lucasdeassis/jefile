package com.dotsub.lucas.jefile.web.rest;

import java.util.Optional;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class MetadataDto {
  
  @NotNull
  @Size(min=2, max=30)
	private String name;
  
  @Size(min=0, max=255)
	private Optional<String> description;
	
	public MetadataDto() {
    // default constructor for Jackson
  }
	
	public MetadataDto(String name, String description) {
		this.name = name;
		this.description = Optional.of(description);
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
