package com.hivein.gateway.service.impl;

import com.hivein.gateway.api.AuthServiceApi;
import com.hivein.gateway.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

    private final AuthServiceApi api;

    @Autowired
    public AuthServiceImpl(AuthServiceApi api) {
        this.api = api;
    }

    @Override
    public boolean validateToken(String token) {
        ResponseEntity<?> response = api.validateToken(token);
        if (response.getStatusCode().value() == 200) {
            return true;
        }
        return false;
    }

    @Override
    public UserDetails getUserDetails(String username) {
        return api.getUserDetails(username);
    }
}
