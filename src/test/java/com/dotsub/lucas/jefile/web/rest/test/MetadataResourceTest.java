package com.dotsub.lucas.jefile.web.rest.test;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.dotsub.lucas.jefile.domain.Metadata;
import com.dotsub.lucas.jefile.service.MetadataService;
import com.dotsub.lucas.jefile.web.rest.MetadataDto;
import com.dotsub.lucas.jefile.web.rest.ResourceTest;
import com.dotsub.lucas.jefile.web.rest.utils.ITUtil;

public class MetadataResourceTest extends ResourceTest {

  @MockBean
  private MetadataService metadataService;

  @MockBean
  MethodArgumentNotValidException methodArgException;

  @Autowired
  private ModelMapper modelMapper;

  @Test
  public void postMetadata() throws Exception {

    MetadataDto metadata = new MetadataDto("testfile.txt",
      "this is a test file !");

    Metadata metadataMock = modelMapper.map(metadata,
      Metadata.class);
    metadataMock.setContentUrl("http://localhost:8080/data/gthe.rar");
    metadataMock.setCreatedAt("2018-06-01T21:10:03.066Z");

    Mockito.when(metadataService.createMetadata(
      ArgumentMatchers.eq(metadataMock),
      ArgumentMatchers.any()))
      .thenReturn(metadataMock);

    this.mvc.perform(MockMvcRequestBuilders.post("/metadata")
      .content(ITUtil.toBytes(metadata))
      .contentType(MediaType.APPLICATION_JSON)
      .accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isAccepted())
      .andExpect(jsonPath("$.name").value(metadataMock.getName()))
      .andExpect(jsonPath("$.description").value(metadataMock
        .getDescription()))
      .andExpect(jsonPath("$.createdAt").value(metadataMock.getCreatedAt()))
      .andExpect(jsonPath("$.contentUrl").value(metadataMock
        .getContentUrl()));
  }

  @Test
  public void postMetadata_noDescription() throws Exception {
    MetadataDto metadata = new MetadataDto("testfile.txt", "");

    Metadata metadataMock = modelMapper.map(metadata,
      Metadata.class);
    metadataMock.setContentUrl("http://localhost:8080/data/gthe.rar");
    metadataMock.setCreatedAt("2018-06-01T21:10:03.066Z");

    Mockito.when(metadataService.createMetadata(
      ArgumentMatchers.eq(metadataMock),
      ArgumentMatchers.any()))
      .thenReturn(metadataMock);

    this.mvc.perform(MockMvcRequestBuilders.post("/metadata")
      .content(ITUtil.toBytes(metadata))
      .contentType(MediaType.APPLICATION_JSON)
      .accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isAccepted())
      .andExpect(jsonPath("$.name").value(metadataMock.getName()))
      .andExpect(jsonPath("$.description").value(metadataMock
        .getDescription()))
      .andExpect(jsonPath("$.createdAt").value(metadataMock.getCreatedAt()))
      .andExpect(jsonPath("$.contentUrl").value(metadataMock
        .getContentUrl()));
  }

  @Test
  public void postMetadata_noName() throws Exception {
    Metadata metadata = new Metadata("", "description but no name");

    this.mvc.perform(MockMvcRequestBuilders.post("/metadata")
      .content(ITUtil.toBytes(metadata))
      .contentType(MediaType.APPLICATION_JSON)
      .accept(MediaType.APPLICATION_JSON))
      .andDo(print())
      .andExpect(status().isBadRequest())
      .andExpect(jsonPath("$.message").value("Validation Error"))
      .andExpect(jsonPath("$.subErrors").isArray());
  }
}
