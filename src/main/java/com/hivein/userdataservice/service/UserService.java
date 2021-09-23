package com.hivein.userdataservice.service;

import com.hivein.userdataservice.model.entity.Profile;
import com.hivein.userdataservice.model.entity.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAllUsers();

    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    User saveUser(User user);

    Optional<User> findByEmail(String email);

    void deleteUserByUsername(String username);

    void updateUser(User user);
}
