package com.hivein.userdataservice.controller;

import com.hivein.userdataservice.model.response.BaseResponse;
import com.hivein.userdataservice.service.UserService;
import com.hivein.userdataservice.util.StatusCodes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserDataController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserDataController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/test")
    public ResponseEntity<?> testMethod() {
        return ResponseEntity.ok(new BaseResponse(StatusCodes.OK.getCode(), "HELLO BEE"));
    }
}
