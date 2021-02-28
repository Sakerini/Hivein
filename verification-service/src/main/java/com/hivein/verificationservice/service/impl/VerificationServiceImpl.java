package com.hivein.verificationservice.service.impl;

import com.hivein.verificationservice.model.email.AccountVerificationEmailContext;
import com.hivein.verificationservice.model.entity.SecureToken;
import com.hivein.verificationservice.service.EmailService;
import com.hivein.verificationservice.service.VerificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Slf4j
@Service
public class VerificationServiceImpl implements VerificationService {

    private final EmailService emailService;

    @Value(value = "${site.base.url.https}")
    private String baseURL;

    @Autowired
    public VerificationServiceImpl(EmailService emailService) {
        this.emailService = emailService;
    }

    @Override
    public void sendVerification(SecureToken verificationInformation) throws MessagingException {
        log.info("sending verification info");

        AccountVerificationEmailContext context = new AccountVerificationEmailContext();
        context.init(verificationInformation);
        context.setToken(verificationInformation.getToken());
        context.buildVerificationUrl(baseURL, verificationInformation.getToken());

        emailService.sendMail(context);
    }
}
