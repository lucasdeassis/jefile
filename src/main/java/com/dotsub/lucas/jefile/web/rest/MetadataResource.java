package com.dotsub.lucas.jefile.web.rest;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dotsub.lucas.jefile.domain.Metadata;
import com.dotsub.lucas.jefile.service.MetadataService;

@RestController
@RequestMapping(value = "/metadata")
public class MetadataResource {

  @Autowired
  private MetadataService metadataService;

  @Autowired
  private ModelMapper modelMapper;

  @RequestMapping(method = RequestMethod.POST)
  @ResponseStatus(HttpStatus.ACCEPTED)
  @ResponseBody
  public Metadata createMetadata(HttpServletRequest request,
      @Valid @RequestBody MetadataDto metadataDto) {
    return metadataService.createMetadata(
      modelMapper.map(metadataDto, Metadata.class), request);
  }
}
