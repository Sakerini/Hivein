package com.hivein.gateway.service;

import javax.servlet.http.HttpServletRequest;

public interface TokenVerificationService {

    String getUsername(String token);

    String extractTokenFromRequest(HttpServletRequest request);

    boolean validateToken(String token);
}
