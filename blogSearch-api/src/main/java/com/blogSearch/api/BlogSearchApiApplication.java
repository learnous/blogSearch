package com.blogSearch.api;

import com.blogSearch.configuration.CommonConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(value = CommonConfig.class)
public class BlogSearchApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlogSearchApiApplication.class, args);
    }
}
