package com.hivein.userdataservice.controller;

import com.hivein.userdataservice.model.entity.User;
import com.hivein.userdataservice.model.response.BaseResponse;
import com.hivein.userdataservice.model.response.ErrorResponse;
import com.hivein.userdataservice.model.dto.UserSummaryDTO;
import com.hivein.userdataservice.service.UserService;
import com.hivein.userdataservice.util.StatusCodes;
import com.hivein.userdataservice.util.UserDataUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

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

    @GetMapping("/find/{username}")
    public ResponseEntity<?> getCurrentUser(@PathVariable(value = "username") String username) {
        Optional<User> user = userService.findByUsername(username);
        if (!user.isPresent()) {
            return new ResponseEntity<>(new ErrorResponse(
                    StatusCodes.BAD_REQUEST.getCode(),
                    "No such user"),
                    HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(UserSummaryDTO
                .builder()
                .id(user.get().getId())
                .username(user.get().getUsername())
                .name(user.get().getUserProfile().getDisplayName())
                .profilePicture(user.get().getUserProfile().getProfilePictureUrl())
                .build());
    }

    @GetMapping("/find/summaries/{username}")
    public ResponseEntity<?> findAllUserSummaries(@PathVariable(value = "username") String username) {
        log.info("retrieving all users summaries");

        return ResponseEntity.ok(userService
                .findAllUsers()
                .stream()
                .filter(user -> !user.getUsername().equals(username))
                .map(UserDataUtil::convertTo));
    }
}
