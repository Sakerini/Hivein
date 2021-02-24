package com.sakerini.hiveinauthservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    public final static String USER = "USER";

    @Id
    @GeneratedValue
    private int id;
    private String role;

    @ManyToOne
    private User user;

    public Role(User user, String role) {
        this.role = role;
        this.user = user;
    }
}
