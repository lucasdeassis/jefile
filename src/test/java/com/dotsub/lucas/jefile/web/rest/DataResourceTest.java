package com.dotsub.lucas.jefile.web.rest;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockPart;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import com.dotsub.lucas.jefile.domain.Data;
import com.dotsub.lucas.jefile.domain.Metadata;
import com.dotsub.lucas.jefile.repository.DataRepository;
import com.dotsub.lucas.jefile.repository.MetadataRepository;
import com.dotsub.lucas.jefile.web.rest.common.ResourceTest;

public class DataResourceTest extends ResourceTest {

  @MockBean
  private MetadataRepository metadataRepository;

  @MockBean
  private DataRepository dataRepository;

  /**
   * see {@linkplain
   * see <a href="https://jira.spring.io/browse/SPR-14252"> MockPart multipart testing</a>
   * @throws Exception
   */
  @Test
  public void postData() throws Exception {

    Metadata metadata = new Metadata("orig",
      "this is a test file !");
    metadata.setContentUrl("http://localhost:8080/data/gthe.rar");
    metadata.setCreatedAt("2018-06-01T21:10:03.066Z");

    byte[] fileContent = "".getBytes(StandardCharsets.UTF_8);
    MockPart filePart = new MockPart("file", ".testfile", fileContent);

    Data data = new Data(metadata, filePart.getName());
    data.setId(1L);

    Mockito.when(metadataRepository.findById(1L))
      .thenReturn(Optional.of(metadata));

    Mockito.when(dataRepository.save(ArgumentMatchers.any()))
      .thenReturn(data);

    this.mvc.perform(MockMvcRequestBuilders.multipart("/data/1")
      .part(filePart))
      .andDo(print())
      .andExpect(status().isCreated())
      .andExpect(jsonPath("$.metadata.name").value(data.getMetadata().getName()))
      .andExpect(jsonPath("$.metadata.description").value(data.getMetadata()
        .getDescription()))
      .andExpect(jsonPath("$.metadata.createdAt").value(data.getMetadata().getCreatedAt()))
      .andExpect(jsonPath("$.metadata.contentUrl").value(data.getMetadata()
        .getContentUrl()))
      .andExpect(jsonPath("$.id").value(data.getId()))
      .andExpect(jsonPath("$.content").value(data.getContent()));
  }

  @Test
  public void postData_tooLarge() throws Exception {

    Metadata metadata = new Metadata("orig",
      "this is a test file !");
    metadata.setContentUrl("http://localhost:8080/data/gthe.rar");
    metadata.setCreatedAt("2018-06-01T21:10:03.066Z");

    MockPart filePart = new MockPart("file", ".testfile", "".getBytes());

    Data data = new Data(metadata, filePart.getName());
    data.setId(1L);

    Mockito.when(metadataRepository.findById(1L))
      .thenReturn(Optional.of(metadata));

    Mockito.when(dataRepository.save(ArgumentMatchers.any()))
      .thenThrow(new MaxUploadSizeExceededException(10_000_000L));

    this.mvc.perform(MockMvcRequestBuilders.multipart("/data/1")
      .part(filePart))
      .andDo(print())
      .andExpect(status().isPayloadTooLarge())
      .andExpect(jsonPath("$.status").value("PAYLOAD_TOO_LARGE"));

  }
}
