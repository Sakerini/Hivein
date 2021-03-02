package com.hivein.verificationservice.controller;

import com.hivein.verificationservice.model.response.BasicResponse;
import com.hivein.verificationservice.model.response.ErrorResponse;
import com.hivein.verificationservice.service.DataService;
import com.hivein.verificationservice.service.JwtService;
import com.hivein.verificationservice.service.SecureTokenService;
import com.hivein.verificationservice.util.StatusCodes;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/verify")
public class VerifyController {

    private final JwtService jwtService;
    private final DataService dataService;
    private final SecureTokenService secureTokenService;

    @Autowired
    public VerifyController(JwtService jwtService, DataService dataService, SecureTokenService secureTokenService) {
        this.jwtService = jwtService;
        this.dataService = dataService;
        this.secureTokenService = secureTokenService;
    }

    @DeleteMapping("/email/{token}")
    public ResponseEntity<?> verifyEmail(@PathVariable(name = "token") String token) {
        log.info("Email Verification Token");
        if (!verifyToken(token)) {
            return ResponseEntity.status(401).body(
                    new ErrorResponse(StatusCodes.UNAUTHORIZED.getCode(), "Token invalid")
            );
        }

        Jws<Claims> jwsClaims =  jwtService.getClaimByToken(token);
        String email = jwsClaims.getBody().getSubject();
        dataService.activateEmail(email);
        secureTokenService.deleteById(secureTokenService.findTokenByEmail(email).get().getId());

        return ResponseEntity.ok(
                new BasicResponse(StatusCodes.OK.getCode(), "Account activated")
        );
    }

    @DeleteMapping("/password/{token}/")
    public ResponseEntity<?> verifyPasswordRetrieve(
            @PathVariable(name = "token") String token,
            @RequestParam(name = "username") String name,
            @RequestParam(name = "password") String password) {
        log.info("Password Retrieve Token");
        if (!verifyToken(token)) {
            return ResponseEntity.status(401).body(
                    new ErrorResponse(StatusCodes.UNAUTHORIZED.getCode(), "Token invalid")
            );
        }
        dataService.changePassword(name, password);

        return ResponseEntity.ok(
                new BasicResponse(StatusCodes.OK.getCode(), "Password Changed")
        );
    }

    private boolean verifyToken(String token) {
        log.info("Verifying Token");
        if (!jwtService.validateToken(token)) {
            log.error("token " + token + " is invalid");
            return false;
        }
        log.info("token " + token + " is verified");
        return true;
    }
}
