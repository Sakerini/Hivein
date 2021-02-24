package com.hivein.authservice.service;

import com.hivein.authservice.exception.BaseException;
import com.hivein.authservice.model.entity.Authority;
import com.hivein.authservice.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    String loginUser(String username, String password);
    User registerUser(User user, Authority role) throws BaseException;
    List<User> findAllUsers();
    Optional<User> findByUsername(String username);
}
