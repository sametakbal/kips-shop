package com.akbal.kips.be.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MessageController {

    private final MessageSource messageSource;

    @GetMapping("/hello/{lang}")
    public String getMessage(@PathVariable String lang) {
        return messageSource.getMessage("welcome.message", null, Locale.of(lang));
    }
}
