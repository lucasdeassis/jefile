package com.dotsub.lucas.jefile.service;

import java.time.Instant;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.dotsub.lucas.jefile.domain.Metadata;

@Service
public class MetadataService {
	private static final Logger logger = LoggerFactory.getLogger(MetadataService.class);
	private static final String template = "%s/%s/content";
	private static final String logCreationTemplate = "Creating file: name:%s, url:%s";

	private final AtomicLong counter = new AtomicLong();

	public Metadata createMetadata(Metadata metadata, String contentBaseUrl) {
		String url = String.format(template, contentBaseUrl, counter.incrementAndGet());
		metadata.setContentUrl(url);
		metadata.setCreatedAt(Instant.now().toString());

		logger.info(String.format(logCreationTemplate, metadata.getName(), metadata.getContentUrl()));

		return metadata;
	}

}
