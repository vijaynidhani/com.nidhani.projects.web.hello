package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@RestController
public class DateTimeController {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @GetMapping("/datetime")
    public String currentDateTime() {
        log.info("GET /datetime called");
        return LocalDateTime.now().format(FORMATTER);
    }

    @GetMapping("/datetime/iso")
    public String currentDateTimeIso() {
        log.info("GET /datetime/iso called");
        return LocalDateTime.now().toString();
    }

    @GetMapping("/datetime/epoch")
    public long currentEpochMillis() {
        log.info("GET /datetime/epoch called");
        return Instant.now().toEpochMilli();
    }
}