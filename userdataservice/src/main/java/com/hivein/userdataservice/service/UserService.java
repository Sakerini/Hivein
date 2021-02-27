package com.hivein.userdataservice.service;

import com.hivein.userdataservice.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAllUsers();

    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    User saveUser(User user);
}
