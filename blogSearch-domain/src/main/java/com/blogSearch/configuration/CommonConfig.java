package com.blogSearch.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = "com.blogSearch")
@EntityScan(basePackages = "com.blogSearch")
@EnableJpaRepositories(basePackages = "com.blogSearch")
public class CommonConfig {
}
