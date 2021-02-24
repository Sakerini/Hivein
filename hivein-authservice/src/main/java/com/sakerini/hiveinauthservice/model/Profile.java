package com.sakerini.hiveinauthservice.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Setter
@Getter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Profile {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    private String displayName;
    private String profilePictureUrl;
    private Date birthday;

    @OneToOne
    @JoinColumn(name = "id")
    private Address address;
}
