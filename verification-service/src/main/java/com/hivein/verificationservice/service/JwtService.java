package com.hivein.verificationservice.service;

import io.jsonwebtoken.Claims;

public interface JwtService {
    String generateToken(String email, long id);
    Claims getClaimByToken(String token);
    boolean validateTokenAndActivateEmail(String token);
}
