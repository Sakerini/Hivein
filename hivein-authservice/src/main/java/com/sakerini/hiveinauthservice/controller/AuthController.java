package com.sakerini.hiveinauthservice.controller;

import com.sakerini.hiveinauthservice.model.User;
import com.sakerini.hiveinauthservice.model.request.LoginRequest;
import com.sakerini.hiveinauthservice.model.request.RegisterRequest;
import com.sakerini.hiveinauthservice.model.response.TokenResponse;
import com.sakerini.hiveinauthservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        log.info("Inside AuthController in /auth/signin method");
        String token = userService.loginUser(loginRequest.getUsername(), loginRequest.getPassword());
        return ResponseEntity.ok(new TokenResponse(token));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest registerRequest) {
        log.info("Inside AuthController in /auth/signup method");

        return null;
    }
}
