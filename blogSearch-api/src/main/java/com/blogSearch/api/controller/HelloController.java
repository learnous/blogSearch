package com.blogSearch.api.controller;

import com.blogSearch.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HelloController {

    @GetMapping("/hello")
    public String getHello() {
        return "Hello World!";
    }
    @GetMapping("/bizException")
    public String getBizException() {
        throw new CustomException("Hello Exception!!");
    }
}
