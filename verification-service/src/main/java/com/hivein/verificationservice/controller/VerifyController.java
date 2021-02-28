package com.hivein.verificationservice.controller;

import com.hivein.verificationservice.model.response.BasicResponse;
import com.hivein.verificationservice.model.response.ErrorResponse;
import com.hivein.verificationservice.service.DataService;
import com.hivein.verificationservice.service.JwtService;
import com.hivein.verificationservice.util.StatusCodes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/verify")
public class VerifyController {

    private final JwtService jwtService;

    @Autowired
    public VerifyController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @DeleteMapping("/email/{token}")
    public ResponseEntity<?> verifyEmail(@PathVariable(name = "token") String token) {
        log.info("Email Verification Token");
        return verifyToken(token);
    }

    @DeleteMapping("/password/{token}")
    public ResponseEntity<?> verifyPasswordRetrieve(@PathVariable(name = "token") String token) {
        log.info("Password Retrieve Token");
        return verifyToken(token);
    }

    private ResponseEntity<?> verifyToken(String token) {
        log.info("Verifying Token");
        if (!jwtService.validateTokenAndActivateEmail(token)) {
            log.error("token " + token + " is invalid");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    new ErrorResponse(StatusCodes.UNAUTHORIZED.getCode(), "Invalid token")
            );
        } else {
            log.info("token " + token + " is verified");
            return ResponseEntity.ok().body(
                    new BasicResponse(StatusCodes.OK.getCode(), "Token is verified")
            );
        }
    }
}
