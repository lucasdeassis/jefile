package com.dotsub.lucas.jefile.web.rest;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.springframework.http.ResponseEntity;


public class HelloControllerIT extends ControllerIT {

    @Test
    public void getHello() throws Exception {
        ResponseEntity<String> response = this.template.getForEntity(base.toString(),
                String.class);
        assertThat(response.getBody(), equalTo("Jefile API"));
    }
}