package com.hivein.verificationservice.service;

import com.hivein.verificationservice.model.email.AbstractEmailContext;

import javax.mail.MessagingException;

public interface EmailService {
    void sendMail(final AbstractEmailContext email) throws MessagingException;
}
