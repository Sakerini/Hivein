package com.hivein.userdataservice.model.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Table(name = "authorities")
@Entity
@NoArgsConstructor
public class Authority {
    public final static String USER = "USER";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
