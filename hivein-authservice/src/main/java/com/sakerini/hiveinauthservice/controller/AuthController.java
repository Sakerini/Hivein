package com.sakerini.hiveinauthservice.controller;

import com.sakerini.hiveinauthservice.entity.request.LoginRequest;
import com.sakerini.hiveinauthservice.entity.request.RegisterRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        //TODO GET TOKEN RETURN TOKEN ELSE RETURN EXCEPTION
        return null;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest registerRequest) {
        //TODO USER BUILDER BUILD USER AND REGISTER USER
        //TODO RETURN SUCCSSFULL REGISTERED
        return null;
    }
}
