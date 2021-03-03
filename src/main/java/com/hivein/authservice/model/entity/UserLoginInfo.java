package com.hivein.authservice.model.entity;

import lombok.*;

import java.util.Set;


@Getter
@Setter
@Builder
@AllArgsConstructor
public class UserLoginInfo {

    private String username;
    private String password;
    private boolean active;
    private Set<Authority> roles;
}
