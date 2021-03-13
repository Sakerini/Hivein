package com.hivein.gateway.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface AuthService {

    boolean validateToken(String token);
    UserDetails getUserDetails(String username);
}
