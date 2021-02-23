package com.sakerini.hiveinauthservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {


    private String id;
    private String username;
    private String password;
    private String email;
    private Instant createdAt;
    private Instant updatedAt;
    private boolean active;
    private Profile userProfile;
}
