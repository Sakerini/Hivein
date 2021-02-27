package com.hivein.verificationservice.controller;

import com.hivein.verificationservice.model.entity.SecureToken;
import com.hivein.verificationservice.model.response.BasicResponse;
import com.hivein.verificationservice.service.JwtService;
import com.hivein.verificationservice.service.SecureTokenService;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/send")
public class EmailSendRequestController {

    private final SecureTokenService secureTokenService;
    private final JwtService jwtService;

    @Autowired
    public EmailSendRequestController(SecureTokenService secureTokenService, JwtService jwtService) {
        this.secureTokenService = secureTokenService;
        this.jwtService = jwtService;
    }

    @PostMapping("/email/{email}")
    public ResponseEntity<?> verifyEmail(@PathVariable(name = "email") String email) {
        SecureToken tokenRecord = SecureToken.builder()
                .email(email)
                .build();
        secureTokenService.saveToken(tokenRecord);
        tokenRecord = secureTokenService.findTokenByEmail(email).get();
        String token = jwtService.generateToken(email, tokenRecord.getId());
        tokenRecord.setToken(token);
        secureTokenService.saveToken(tokenRecord);

        return ResponseEntity.ok(new BasicResponse("code-200", "Email sent"));
        /**
         * TODO:
         * 3. Create Url + token
         * 4. Send EMAIL
         */
    }
}
