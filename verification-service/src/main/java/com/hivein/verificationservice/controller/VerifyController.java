package com.hivein.verificationservice.controller;

import com.hivein.verificationservice.model.response.BasicResponse;
import com.hivein.verificationservice.model.response.ErrorResponse;
import com.hivein.verificationservice.service.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/verify")
public class VerifyController {

    private final JwtService jwtService;

    @Autowired
    public VerifyController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @GetMapping("/email/{token}")
    public ResponseEntity<?> verifyToken(@PathVariable(name = "token") String token) {
        log.info("Verifying Token");
        if (!jwtService.validateToken(token)) {
            log.error("token " + token + " is invalid");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponse("code-401", "Invalid token"));
        } else {
            log.info("token " + token + " is verified");
            return ResponseEntity.ok().body(new BasicResponse("code-200", "Token is verified"));
        }
    }
}
