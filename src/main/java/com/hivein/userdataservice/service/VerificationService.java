package com.hivein.userdataservice.service;

import com.hivein.userdataservice.exception.EmailNotSentException;

public interface VerificationService {
    void sendVerificationEmail(String greetingName, String email) throws EmailNotSentException;
}
