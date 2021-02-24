package com.sakerini.hiveinauthservice.model;

import lombok.*;

import java.util.Date;
import java.util.Set;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Profile {

    private String displayName;
    private String profilePictureUrl;
    private Date birthday;
    private Set<Address> addresses;
}
