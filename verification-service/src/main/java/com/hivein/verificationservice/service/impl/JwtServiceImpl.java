package com.hivein.verificationservice.service.impl;

import com.hivein.verificationservice.config.JwtConfig;
import com.hivein.verificationservice.service.JwtService;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
public class JwtServiceImpl implements JwtService {

    private final JwtConfig jwtConfig;

    @Autowired
    public JwtServiceImpl(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    @Override
    public String generateToken(String email, int id) {
        log.info("Generating token... Jwt Service");

        Long timeNow = System.currentTimeMillis();

        return Jwts.builder()
                .setSubject(email)
                .claim("id", id)
                .setIssuedAt(new Date(timeNow))
                .setExpiration(new Date(timeNow + jwtConfig.getExpiration() * 1000))
                .signWith(SignatureAlgorithm.HS512, jwtConfig.getSecret().getBytes())
                .compact();
    }

    @Override
    public Claims getClaimByToken(String token) {
        log.info("Inside Jwt Service getClaimByToken");
        return Jwts.parser()
                .setSigningKey(jwtConfig.getSecret().getBytes())
                .parseClaimsJws(token)
                .getBody();
    }

    @Override
    public boolean validateToken(String token) {
        log.info("Validating token JWTService");
        try {
            Jwts.parser()
                    .setSigningKey(jwtConfig.getSecret().getBytes())
                    .parseClaimsJws(token);
            return true;
        } catch (SignatureException ex) {
            log.error("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty.");
        }
        return false;
    }
}
