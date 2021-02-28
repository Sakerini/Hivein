package com.hivein.userdataservice.controller;

import com.hivein.userdataservice.exception.EmailNotFoundException;
import com.hivein.userdataservice.exception.UsernameNotFoundException;
import com.hivein.userdataservice.model.dto.RoleDTO;
import com.hivein.userdataservice.model.entity.Authority;
import com.hivein.userdataservice.model.entity.User;
import com.hivein.userdataservice.model.response.AuthInformationResponse;
import com.hivein.userdataservice.model.response.BaseResponse;
import com.hivein.userdataservice.service.UserService;
import com.hivein.userdataservice.util.StatusCodes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@RestController
@RequestMapping
public class UserDataController {

    private final UserService userService;

    @Autowired
    public UserDataController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping("/activate/{email}")
    public ResponseEntity<?> activateAccount(@PathVariable(name = "email") String email) throws EmailNotFoundException {
        Optional<User> userOptional = userService.findByEmail(email);
        if (!userOptional.isPresent()) {
            log.error("ERROR: Activating account Email not found " + email);
            throw new EmailNotFoundException(StatusCodes.NOT_FOUND.getCode(), "Email not found");
        }

        User user = userOptional.get();
        user.setActive(true);
        userService.saveUser(user);
        return ResponseEntity.ok(new BaseResponse(StatusCodes.OK.getCode(), "User activated"));
    }

    @GetMapping("/get-authinfo/{username}")
    public ResponseEntity<?> getUserAuthInfo(
            @PathVariable(name = "username") String username) throws UsernameNotFoundException {
        log.info("Inside UserDataController getting user authentication information ${}", username);

        Optional<User> optionalUser = userService.findByUsername(username);
        if (!optionalUser.isPresent()) {
            log.error("ERROR: GetAuth info Username not found " + username);
            throw new UsernameNotFoundException(StatusCodes.NOT_FOUND.getCode(), "Username not found");
        }

        User user = optionalUser.get();
        Set<RoleDTO> userRoles = new HashSet<>();

        for (Authority role : user.getRoles()) {
            userRoles.add(new RoleDTO(role.getUser().getUsername(), role.getRole()));
        }

        AuthInformationResponse response =
                new AuthInformationResponse(user.getUsername(), user.getPassword(), user.isActive(), userRoles);

        return ResponseEntity.ok(response);
    }
}
