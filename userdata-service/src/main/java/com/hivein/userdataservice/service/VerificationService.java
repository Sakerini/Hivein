package com.hivein.userdataservice.service;

import com.hivein.userdataservice.exception.EmailNotSentException;

public interface VerificationService {
    void sendVerificationEmail(String email) throws EmailNotSentException;
}
