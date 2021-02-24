package com.sakerini.hiveinauthservice.service.impl;

import com.sakerini.hiveinauthservice.exception.BaseException;
import com.sakerini.hiveinauthservice.exception.EmailAlreadyExistsException;
import com.sakerini.hiveinauthservice.exception.UsernameAlreadyExistsException;
import com.sakerini.hiveinauthservice.model.entity.Authority;
import com.sakerini.hiveinauthservice.model.entity.User;
import com.sakerini.hiveinauthservice.repository.UserRepository;
import com.sakerini.hiveinauthservice.service.JwtService;
import com.sakerini.hiveinauthservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, JwtService jwtService,
                           AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String loginUser(String username, String password) {
        log.info("Inside UserService loginUser method");

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password));

        return jwtService.generateToken(authentication);
    }

    @Override
    public User registerUser(User user, Authority role) throws BaseException {
        log.info("registering user {}", user.getUsername());

        if (userRepository.existsByUsername(user.getUsername())) {
            log.warn("username {} already exists.", user.getUsername());

            throw new UsernameAlreadyExistsException("code-409",
                    String.format("username %s already exists", user.getUsername()));
        }

        if (userRepository.existsByEmail(user.getEmail())) {
            log.warn("email {} already exists.", user.getEmail());

            throw new EmailAlreadyExistsException("code-409",
                    String.format("email %s already exists", user.getEmail()));
        }

        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        HashSet<Authority> authorities = new HashSet<>();
        authorities.add(role);
        Instant instant = Instant.now();
        user.setCreatedAt(instant);
        user.setUpdatedAt(instant);
        user.setRoles(authorities);

        return userRepository.save(user);
    }

    @Override
    public List<User> findAllUsers() {
        log.info("retrieving all users");
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findByUsername(String username) {
        log.info("retrieving user {}", username);
        return userRepository.findByUsername(username);
    }
}
