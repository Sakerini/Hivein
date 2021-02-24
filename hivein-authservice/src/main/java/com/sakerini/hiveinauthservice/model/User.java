package com.sakerini.hiveinauthservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.active = true;
        this.roles = new HashSet<>() {{ new Role(Role.USER); }};
    }

    @Id
    private String id;
    @NotBlank
    @Size(max = 15)
    @JsonIgnore
    private String username;

    @NotBlank
    @Size(max = 40)
    @JsonIgnore
    private String password;

    @NotBlank
    @Size(max = 40)
    @Email
    private String email;

    @CreatedDate
    private Instant createdAt;

    @LastModifiedDate
    private Instant updatedAt;

    private boolean active;
    private Profile userProfile;
    private Set<Role> roles;
}
