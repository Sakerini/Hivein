package com.sakerini.hiveinauthservice.service;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.Authentication;

public interface JwtService {
    String generateToken(Authentication authentication);
    Claims getClaimByToken(String token);
    boolean validateToken(String token);
}
