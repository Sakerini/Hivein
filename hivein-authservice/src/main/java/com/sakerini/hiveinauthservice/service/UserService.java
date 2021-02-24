package com.sakerini.hiveinauthservice.service;

import com.sakerini.hiveinauthservice.exception.BaseException;
import com.sakerini.hiveinauthservice.model.entity.Authority;
import com.sakerini.hiveinauthservice.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    String loginUser(String username, String password);
    User registerUser(User user, Authority role) throws BaseException;
    List<User> findAllUsers();
    Optional<User> findByUsername(String username);
}
