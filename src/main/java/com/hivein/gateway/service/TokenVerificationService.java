package com.hivein.gateway.service;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

public interface TokenVerificationService {

    String extractTokenFromRequest(HttpServletRequest request);
    boolean validateToken(String token);
}
