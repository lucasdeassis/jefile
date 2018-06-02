package com.dotsub.lucas.jefile.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories("com.dotsub.lucas.jefile.repository")
@EnableTransactionManagement
public class DatabaseConfiguration {
}
