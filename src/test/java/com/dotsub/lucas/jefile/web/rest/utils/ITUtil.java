package com.dotsub.lucas.jefile.web.rest.utils;

import java.io.IOException;
import java.nio.charset.Charset;

import org.springframework.http.MediaType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ITUtil {

  public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON
    .getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

  public static byte[] toBytes(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsBytes(object);
  }
  
}
