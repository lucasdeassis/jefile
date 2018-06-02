package com.dotsub.lucas.jefile.web.rest.test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.springframework.http.ResponseEntity;

import com.dotsub.lucas.jefile.web.rest.ResourceIT;

public class HelloResourceIT extends ResourceIT {

  @Test
  public void getHello() throws Exception {
    ResponseEntity<String> response = this.template.getForEntity(base.toString(),
      String.class);
    assertThat(response.getBody(), equalTo("Jefile API"));
  }
}
