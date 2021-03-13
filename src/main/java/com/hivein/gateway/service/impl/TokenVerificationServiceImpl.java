package com.hivein.gateway.service.impl;

import com.hivein.gateway.service.AuthService;
import com.hivein.gateway.service.TokenVerificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Service
public class TokenVerificationServiceImpl implements TokenVerificationService {

    private final AuthService authService;

    @Autowired
    public TokenVerificationServiceImpl(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public String extractTokenFromRequest(HttpServletRequest req) {
        log.info("Extracting token");
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    @Override
    public boolean validateToken(String token) {
        if (authService.validateToken(token)) {
            return true;
        }
        return false;
    }
}
