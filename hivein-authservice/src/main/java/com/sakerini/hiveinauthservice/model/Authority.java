package com.sakerini.hiveinauthservice.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Table(name = "authorities")
@Entity
public class Authority {
    public final static String USER = "USER";

    @Id
    @GeneratedValue
    private long authority_id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    private String role;

    public Authority(User user, String role) {
        this.user = user;
        this.role = role;
    }
}
