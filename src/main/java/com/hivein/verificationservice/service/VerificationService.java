package com.hivein.verificationservice.service;

import com.hivein.verificationservice.model.entity.SecureToken;

import javax.mail.MessagingException;

public interface VerificationService {
    void sendVerification(SecureToken verificationInformation) throws MessagingException;

    void sendPasswordChangeVerification(SecureToken verificationInformation) throws MessagingException;
}
