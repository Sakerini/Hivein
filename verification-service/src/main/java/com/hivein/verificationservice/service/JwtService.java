package com.hivein.verificationservice.service;

import io.jsonwebtoken.Claims;

public interface JwtService {
    String generateToken(String email, int id);
    Claims getClaimByToken(String token);
    boolean validateToken(String token);
}
