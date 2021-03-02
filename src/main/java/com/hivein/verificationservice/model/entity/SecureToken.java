package com.hivein.verificationservice.model.entity;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "tokens")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SecureToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String email;
    private String token;
    private String state;
}
