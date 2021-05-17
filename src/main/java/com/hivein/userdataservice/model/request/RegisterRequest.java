package com.hivein.userdataservice.model.request;

import com.hivein.userdataservice.model.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    private String username;
    private String email;
    private String password;
    private String displayName;
    private String firstName;
    private String lastName;
    private String profilePicUrl;
    private Date birthDay;
    private Address address;
}
