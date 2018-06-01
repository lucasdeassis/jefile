package com.dotsub.lucas.jefile.web.rest;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.dotsub.lucas.jefile.web.rest.utils.ITUtil;

public class MetadataResourceTest extends ResourceTest {

  @Test
  public void postMetadata() throws Exception {

    MetadataDto metadata = new MetadataDto("testfile.txt",
      "this is a test file !");

    this.mvc.perform(MockMvcRequestBuilders.post("/metadata")
      .content(ITUtil.toBytes(metadata))
      .contentType(MediaType.APPLICATION_JSON)
      .accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isAccepted())
      .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(metadata.getName()))
      .andExpect(MockMvcResultMatchers.jsonPath("$.description").value(metadata.getDescription()))
      .andExpect(MockMvcResultMatchers.jsonPath("$.createdAt").exists())
      .andExpect(MockMvcResultMatchers.jsonPath("$.contentUrl").exists());
  }

  @Test
  public void postMetadata_noDescription() throws Exception {
    MetadataDto metadata = new MetadataDto("testfile.txt", "");

    this.mvc.perform(MockMvcRequestBuilders.post("/metadata")
      .content(ITUtil.toBytes(metadata))
      .contentType(MediaType.APPLICATION_JSON)
      .accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isAccepted())
      .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(metadata.getName()))
      .andExpect(MockMvcResultMatchers.jsonPath("$.description").value(metadata.getDescription()))
      .andExpect(MockMvcResultMatchers.jsonPath("$.createdAt").exists())
      .andExpect(MockMvcResultMatchers.jsonPath("$.contentUrl").exists());
  }
  
  @Test
  public void postMetadata_noName() throws Exception {
    MetadataDto metadata = new MetadataDto("", "");

    this.mvc.perform(MockMvcRequestBuilders.post("/metadata")
      .content(ITUtil.toBytes(metadata))
      .contentType(MediaType.APPLICATION_JSON)
      .accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isBadRequest());
//      .andExpect(MockMvcResultMatchers.jsonPath("$.fieldError").exists());
  }
}
