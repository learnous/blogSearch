package com.blogSearch.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.blogSearch")
@EntityScan(basePackages = "com.blogSearch")
public class BlogSearchApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlogSearchApiApplication.class, args);
    }
}
