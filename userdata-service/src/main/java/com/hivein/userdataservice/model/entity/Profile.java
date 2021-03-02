package com.hivein.userdataservice.model.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@Table(name = "profiles")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String displayName;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    private String profilePictureUrl;

    @NotNull
    private Date birthday;

    @NotNull
    @OneToOne(orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_fk")
    private Address address;
}
