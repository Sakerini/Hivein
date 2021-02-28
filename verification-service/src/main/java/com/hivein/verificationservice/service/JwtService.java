package com.hivein.verificationservice.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

public interface JwtService {
    String generateToken(String email, long id);
    Jws<Claims> getClaimByToken(String token);
    boolean validateToken(String token);
}
