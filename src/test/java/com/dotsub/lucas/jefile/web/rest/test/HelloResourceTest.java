package com.dotsub.lucas.jefile.web.rest.test;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.dotsub.lucas.jefile.web.rest.ResourceTest;

public class HelloResourceTest extends ResourceTest {

  @Test
  public void getHello() throws Exception {
    this.mvc.perform(MockMvcRequestBuilders.get("/").accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(content().string(equalTo("Jefile API")));
  }
}
