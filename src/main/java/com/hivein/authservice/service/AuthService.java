package com.hivein.authservice.service;

public interface AuthService {
    String authenticate(String username, String password);
}
