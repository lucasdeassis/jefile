package com.dotsub.lucas.jefile.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Properties specific to Jefile.
 * <p>
 * Properties are configured in the application.yml file.
 */
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {

}
