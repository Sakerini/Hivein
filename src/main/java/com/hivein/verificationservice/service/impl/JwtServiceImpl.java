package com.hivein.verificationservice.service.impl;

import com.hivein.verificationservice.config.JwtConfig;
import com.hivein.verificationservice.model.entity.SecureToken;
import com.hivein.verificationservice.service.JwtService;
import com.hivein.verificationservice.service.SecureTokenService;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Slf4j
@Service
public class JwtServiceImpl implements JwtService {

    private final JwtConfig jwtConfig;
    private final SecureTokenService secureTokenService;

    @Autowired
    public JwtServiceImpl(JwtConfig jwtConfig, SecureTokenService secureTokenService) {
        this.jwtConfig = jwtConfig;
        this.secureTokenService = secureTokenService;
    }

    @Override
    public String generateToken(String email, long id) {
        log.info("Generating token... Jwt Service");

        Long timeNow = System.currentTimeMillis();

        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(email)
                .claim("id", id)
                .setIssuedAt(new Date(timeNow))
                .setExpiration(new Date(timeNow + jwtConfig.getExpiration() * 1000))
                .signWith(SignatureAlgorithm.HS512, jwtConfig.getSecret().getBytes())
                .compact();
    }

    @Override
    public Jws<Claims> getClaimByToken(String token) {
        log.info("Inside Jwt Service getClaimByToken");
        return Jwts.parser()
                .setSigningKey(jwtConfig.getSecret().getBytes())
                .parseClaimsJws(token);
    }

    @Override
    public boolean validateToken(String token) {
        log.info("Validating token JWTService");
        try {
            Jws<Claims> claims = Jwts.parser()
                    .setSigningKey(jwtConfig.getSecret().getBytes())
                    .parseClaimsJws(token);
            long id = Long.parseLong(claims.getBody().get("id").toString());
            String email = claims.getBody().getSubject();
            Optional<SecureToken> tokenRecord = secureTokenService.findTokenByEmail(email);
            if (!tokenRecord.isPresent()) {
                throw new MalformedJwtException("Invalid Token");
            }
            if (id != tokenRecord.get().getId()) {
                throw new MalformedJwtException("Invalid Token");
            }

           // dataService.activateEmail(email);
            // secureTokenService.deleteById(id);
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
