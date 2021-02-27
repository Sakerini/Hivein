package com.hivein.userdataservice.controller;

import com.hivein.userdataservice.exception.UsernameNotFoundException;
import com.hivein.userdataservice.model.entity.User;
import com.hivein.userdataservice.model.response.AuthInformationResponse;
import com.hivein.userdataservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping
public class UserDataController {

    private final UserService userService;

    @Autowired
    public UserDataController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/get-authinfo/{username}")
    public ResponseEntity<?> getUserByUsername(
            @PathVariable(name = "username") String username) throws UsernameNotFoundException {
        log.info("Inside UserDataController getting user authentication information ${}", username);

        Optional<User> optionalUser = userService.findByUsername(username);
        if (!optionalUser.isPresent()) {
            log.error("ERROR: GetAuth info Username not found ${]", username);
            throw new UsernameNotFoundException("code-404", "Username not found");
        }

        User user = optionalUser.get();

        AuthInformationResponse response =
                new AuthInformationResponse(user.getUsername(), user.getPassword(), user.isActive());

        return ResponseEntity.ok(response);
    }
}
