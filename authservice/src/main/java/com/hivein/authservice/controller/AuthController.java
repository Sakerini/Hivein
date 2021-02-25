package com.hivein.authservice.controller;

import com.hivein.authservice.exception.BaseException;
import com.hivein.authservice.model.entity.Authority;
import com.hivein.authservice.model.entity.Profile;
import com.hivein.authservice.model.entity.User;
import com.hivein.authservice.model.request.LoginRequest;
import com.hivein.authservice.model.request.RegisterRequest;
import com.hivein.authservice.service.UserService;
import com.hivein.authservice.exception.BadRequestException;
import com.hivein.authservice.model.response.RegisterResponse;
import com.hivein.authservice.model.response.TokenResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
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
                                .birthday(registerRequest.getBirthDay())
                                .address(registerRequest.getAddress())
                                .build())
                .build();

        try {
            userService.registerUser(user, new Authority(user , Authority.USER));
        } catch (BaseException e) {
            throw new BadRequestException("code-400", e.getMessage());
        }

        return ResponseEntity.ok(new RegisterResponse("User registered successfully"));
    }
}
