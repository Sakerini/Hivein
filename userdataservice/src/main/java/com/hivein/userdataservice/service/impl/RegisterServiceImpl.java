package com.hivein.userdataservice.service.impl;

import com.hivein.userdataservice.exception.BaseException;
import com.hivein.userdataservice.exception.EmailAlreadyExistsException;
import com.hivein.userdataservice.exception.UsernameAlreadyExistsException;
import com.hivein.userdataservice.model.entity.Authority;
import com.hivein.userdataservice.model.entity.User;
import com.hivein.userdataservice.service.RegisterService;
import com.hivein.userdataservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.HashSet;

@Slf4j
@Service
public class RegisterServiceImpl implements RegisterService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegisterServiceImpl(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void registerUser(User user, Authority role) throws BaseException {
        log.info("registering user {}", user.getUsername());

        if (userService.existsByUsername(user.getUsername())) {
            log.warn("username {} already exists.", user.getUsername());

            throw new UsernameAlreadyExistsException("code-409",
                    String.format("username %s already exists", user.getUsername()));
        }

        if (userService.existsByEmail(user.getEmail())) {
            log.warn("email {} already exists.", user.getEmail());

            throw new EmailAlreadyExistsException("code-409",
                    String.format("email %s already exists", user.getEmail()));
        }

        user.setActive(false);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        HashSet<Authority> authorities = new HashSet<>();
        authorities.add(role);
        Instant instant = Instant.now();
        user.setCreatedAt(instant);
        user.setUpdatedAt(instant);
        user.setRoles(authorities);
        userService.saveUser(user);

        //TODO FEIGN CALL EMAIL SERVICE or CONFIRMATION EMAIL HERE
    }
}