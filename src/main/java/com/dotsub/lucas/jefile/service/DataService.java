package com.dotsub.lucas.jefile.service;

import java.io.File;
import java.io.IOException;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dotsub.lucas.jefile.domain.Data;
import com.dotsub.lucas.jefile.domain.Metadata;
import com.dotsub.lucas.jefile.repository.DataRepository;
import com.dotsub.lucas.jefile.repository.MetadataRepository;
import com.dotsub.lucas.jefile.service.util.FileUtils;
import com.dotsub.lucas.jefile.web.rest.errors.JeFileNotFoundException;

@Service
public class DataService {
  private static final Logger logger = LoggerFactory.getLogger(MetadataService.class);
  private static final String logUploadTemplate = "Uploading file: name:%s, length:%s";

  @Autowired
  private DataRepository dataRepository;

  @Autowired
  private MetadataRepository metadataRepository;

  @Transactional
  public Data upload(String id, MultipartFile multipartFile) throws IOException {
    File file = FileUtils.convertMultiPartToFile(multipartFile);

    Metadata metadata = metadataRepository.findById(Long.parseLong(id)).orElseThrow(JeFileNotFoundException::new);

    logger.info(String.format(logUploadTemplate,
      metadata.getName(), file.length()));

    return dataRepository.save(new Data(metadata, String.valueOf(file.length())));
  }

}
