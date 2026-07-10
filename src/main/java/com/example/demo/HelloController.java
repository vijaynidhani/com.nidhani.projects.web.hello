package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HelloController {

    @GetMapping("/")
    public String hello() {
        log.info("GET / called");
        return "Hello, World!";
    }

    @GetMapping("/hello")
    public String helloName() {
        log.info("GET /hello called");
        return "Hello, World from Spring Boot!";
    }
}