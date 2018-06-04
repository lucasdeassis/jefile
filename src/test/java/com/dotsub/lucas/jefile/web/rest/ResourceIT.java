package com.dotsub.lucas.jefile.web.rest;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class ResourceIT {

  @LocalServerPort
  private int port;

  protected URL base;

  @Autowired
  protected TestRestTemplate template;

  @Before
  public void setUp() throws MalformedURLException {
    this.base = new URL("http://localhost:" + port + "/");
  }
}
