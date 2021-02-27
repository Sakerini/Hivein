package com.hivein.userdataservice.controller;

import com.hivein.userdataservice.exception.BaseException;
import com.hivein.userdataservice.model.entity.Authority;
import com.hivein.userdataservice.model.entity.Profile;
import com.hivein.userdataservice.model.entity.User;
import com.hivein.userdataservice.model.request.RegisterRequest;
import com.hivein.userdataservice.model.response.RegisterResponse;
import com.hivein.userdataservice.service.RegisterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class RegisterController {

    private final RegisterService registerService;

    public RegisterController(RegisterService userService) {
        this.registerService = userService;
    }


    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest registerRequest) throws BaseException {
        log.info("Inside RegisterController in /user/signup method");

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
            registerService.registerUser(user, new Authority(user, Authority.USER));
        } catch (BaseException e) {

            throw new BaseException("code-400", e.getMessage());
        }

        return ResponseEntity.ok(new RegisterResponse("User registered successfully"));
    }
}