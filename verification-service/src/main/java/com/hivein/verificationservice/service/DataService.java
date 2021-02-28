package com.hivein.verificationservice.service;

public interface DataService {
    void activateEmail(String email);
    void changePassword(String username, String password);
}
