package com.sakerini.hiveinauthservice.controller;

import com.sakerini.hiveinauthservice.exception.BadRequestException;
import com.sakerini.hiveinauthservice.exception.BaseException;
import com.sakerini.hiveinauthservice.model.Profile;
import com.sakerini.hiveinauthservice.model.Role;
import com.sakerini.hiveinauthservice.model.User;
import com.sakerini.hiveinauthservice.model.request.LoginRequest;
import com.sakerini.hiveinauthservice.model.request.RegisterRequest;
import com.sakerini.hiveinauthservice.model.response.RegisterResponse;
import com.sakerini.hiveinauthservice.model.response.TokenResponse;
import com.sakerini.hiveinauthservice.service.UserService;
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
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest registerRequest) throws BadRequestException {
        log.info("Inside AuthController in /auth/signup method");

        User user = User.builder()
                .username(registerRequest.getUsername())
                .email(registerRequest.getEmail())
                .password(registerRequest.getPassword())
                .userProfile(
                        Profile
                                .builder()
                                .displayName(registerRequest.getName())
                                .profilePictureUrl("NULL")
                                .build())
                .build();

        try {
            userService.registerUser(user, new Role(user, Role.USER));
        } catch (BaseException e) {
            throw new BadRequestException("code-400", e.getMessage());
        }

        return ResponseEntity.ok(new RegisterResponse("User registered successfully"));
    }
}
