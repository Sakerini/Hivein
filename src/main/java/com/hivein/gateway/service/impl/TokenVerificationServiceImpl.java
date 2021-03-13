package com.hivein.gateway.service.impl;

import com.hivein.gateway.service.ResourceService;
import com.hivein.gateway.service.TokenVerificationService;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Service
public class TokenVerificationServiceImpl implements TokenVerificationService {

    @Value("${security.jwt.uri:}")
    private String Uri;

    @Value("${security.jwt.header:}")
    private String header;

    @Value("${security.jwt.prefix:}")
    private String prefix;

    @Value("${security.jwt.expiration:1234}")
    private int expiration;

    @Value("${security.jwt.secret:}")
    private String secret;

    private final ResourceService resourceService;

    @Autowired
    public TokenVerificationServiceImpl(ResourceService resourceService) {
        this.resourceService = resourceService;
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
        log.info("Validating token");
        try {
            Jwts.parser()
                    .setSigningKey(secret.getBytes())
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

    @Override
    public String getUsername(String token) {
        return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody().getSubject();
    }
}
