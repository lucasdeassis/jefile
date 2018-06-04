package com.dotsub.lucas.jefile.web.rest;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dotsub.lucas.jefile.domain.Data;
import com.dotsub.lucas.jefile.service.DataService;

@RestController
@RequestMapping(value = "/data")
public class DataResource {

  @Autowired
  private DataService dataService;

  @PostMapping(value = "/{id}")
  @ResponseStatus(HttpStatus.CREATED)
  @ResponseBody
  public Data upload(@PathVariable("id") String id,
      @RequestPart(value = "file") MultipartFile file) throws IOException {
    return dataService.upload(id, file);
  }
}
