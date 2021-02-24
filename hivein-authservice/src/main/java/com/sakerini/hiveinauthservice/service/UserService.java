package com.sakerini.hiveinauthservice.service;

import com.sakerini.hiveinauthservice.exception.BaseException;
import com.sakerini.hiveinauthservice.model.Role;
import com.sakerini.hiveinauthservice.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    String loginUser(String username, String password);
    User registerUser(User user, Role role) throws BaseException;
    List<User> findAllUsers();
    Optional<User> findByUsername(String username);
}
