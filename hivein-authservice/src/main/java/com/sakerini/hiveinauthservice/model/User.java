package com.sakerini.hiveinauthservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.Set;

@Setter
@Getter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue
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
    @OneToOne
    @JoinColumn(name = "id")
    private Profile userProfile;

    @OneToMany(mappedBy = "id")
    private Set<Role> roles;
}
