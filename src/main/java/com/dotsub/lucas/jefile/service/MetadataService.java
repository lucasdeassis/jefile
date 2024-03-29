package com.dotsub.lucas.jefile.service;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dotsub.lucas.jefile.domain.Metadata;
import com.dotsub.lucas.jefile.repository.MetadataRepository;

@Service
public class MetadataService {
  private static final Logger logger = LoggerFactory.getLogger(MetadataService.class);
  private static final String template = "%s/data/%s";
  private static final String logCreationTemplate = "Creating file: name:%s, url:%s";
  private static final String baseUrlTemplate = "%s://%s:%s";

  @Autowired
  private MetadataRepository metadataRepository;

  @Transactional
  public Metadata createMetadata(Metadata metadata, HttpServletRequest request) {

    if (metadataRepository.existsByName(metadata.getName()))
      throw new ConstraintViolationException(String.format(
        "File metadata with name %s already exists", metadata.getName()), null);

    Metadata newMetadata = metadataRepository.save(metadata);

    String contentBaseUrl = String.format(
      baseUrlTemplate, request.getScheme(), request.getServerName(), request.getServerPort());

    String url = String.format(template, contentBaseUrl, newMetadata.getId());

    newMetadata.setContentUrl(url);
    newMetadata.setCreatedAt(Instant.now().toString());

    metadataRepository.save(newMetadata);

    logger.info(String.format(logCreationTemplate, newMetadata.getName(), newMetadata
      .getContentUrl()));

    return metadata;
  }

}
