package com.dotsub.lucas.jefile.web.rest;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MetadataDto {

  @NotNull
  @Size(min = 2, max = 30, message = "File name must have between 2 and 30 characters")
  @Pattern(regexp = "^[^*&%\\s]+$", message = "Filename pattern must not contain *, &, % and spaces")
  private String name;

  @Size(min = 0, max = 255,
      message = "Description is too long. It must have up to 255 characters")
  private String description;

  public MetadataDto() {
    // default constructor for Jackson
  }

  public MetadataDto(String name, String description) {
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
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
