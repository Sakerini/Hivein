package com.hivein.verificationservice.controller;

import com.hivein.verificationservice.model.entity.SecureToken;
import com.hivein.verificationservice.model.response.BasicResponse;
import com.hivein.verificationservice.service.JwtService;
import com.hivein.verificationservice.service.SecureTokenService;
import com.hivein.verificationservice.service.VerificationService;
import com.hivein.verificationservice.util.StatusCodes;
import com.hivein.verificationservice.util.VerificationStates;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@Slf4j
@RestController
@RequestMapping("/send")
public class EmailSendRequestController {

    private VerificationService verificationService;
    private final SecureTokenService secureTokenService;
    private final JwtService jwtService;

    @Autowired
    public EmailSendRequestController(SecureTokenService secureTokenService, JwtService jwtService,VerificationService verificationService ) {
        this.secureTokenService = secureTokenService;
        this.jwtService = jwtService;
        this.verificationService = verificationService;
    }

    @PostMapping("/email")
    public ResponseEntity<?> verifyEmail(
            @RequestParam(name = "name") String name, @RequestParam(name = "email") String email) throws MessagingException {
        SecureToken tokenRecord = SecureToken.builder()
                .name(name)
                .email(email)
                .state(VerificationStates.EMAIL.getState())
                .build();
        secureTokenService.saveToken(tokenRecord);
        tokenRecord = secureTokenService.findTokenByEmail(email).get();
        String token = jwtService.generateToken(email, tokenRecord.getId());
        tokenRecord.setToken(token);
        secureTokenService.saveToken(tokenRecord);
        log.info("Generated token saved");
        verificationService.sendVerification(tokenRecord);
        return ResponseEntity.ok(new BasicResponse(StatusCodes.OK.getCode(), "Email sent"));
    }
}
