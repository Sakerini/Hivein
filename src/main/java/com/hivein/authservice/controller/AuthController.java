package com.hivein.authservice.controller;

import com.hivein.authservice.model.request.LoginRequest;
import com.hivein.authservice.model.response.ErrorResponse;
import com.hivein.authservice.model.response.TokenInvalidResponse;
import com.hivein.authservice.model.response.TokenResponse;
import com.hivein.authservice.service.AuthService;
import com.hivein.authservice.service.JwtService;
import com.hivein.authservice.util.StatusCodes;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Autowired
    public AuthController(AuthService authService, JwtService jwtService,
                          @Qualifier(value = "authUserDetailsService") UserDetailsService userDetailsService) {
        this.authService = authService;
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        log.info("Inside AuthController in /auth/signin method");
        String token = authService.authenticate(loginRequest.getUsername(), loginRequest.getPassword());
        return ResponseEntity.ok(new TokenResponse(token));
    }

    @GetMapping("/validate-token")
    public ResponseEntity<?> validateToken(@RequestParam(name = "token") String token) {
        if (jwtService.validateToken(token)) {
            return ResponseEntity.ok("");
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                new TokenInvalidResponse(StatusCodes.FORBIDDEN.getCode(), "Invalid Token")
        );
    }

    @GetMapping("/user-info")
    public UserDetails getUserInfo(@RequestParam(name = "username") String username) {
        return userDetailsService.loadUserByUsername(username);
    }
}
