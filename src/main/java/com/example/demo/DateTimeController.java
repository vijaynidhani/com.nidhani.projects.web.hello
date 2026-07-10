package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
public class DateTimeController {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @GetMapping("/datetime")
    public String currentDateTime() {
        return LocalDateTime.now().format(FORMATTER);
    }

    @GetMapping("/datetime/iso")
    public String currentDateTimeIso() {
        return LocalDateTime.now().toString();
    }
}