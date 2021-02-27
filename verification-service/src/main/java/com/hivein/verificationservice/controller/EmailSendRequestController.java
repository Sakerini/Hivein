package com.hivein.verificationservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping
public class EmailSendRequestController {

    @PostMapping("/verify/{email}")
    public void verifyEmail(@PathVariable(name = "email") String email) {
        /**
         * TODO:
         * 1. Create Token
         * 2. Save Token
         * 3. Create Url + token
         * 4. Send EMAIL
         */
    }
}
