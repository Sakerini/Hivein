package com.hivein.userdataservice.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Setter
@Getter
@Builder
@AllArgsConstructor
public class UserSummaryDTO {

    @JsonProperty("id")
    private long id;
    @JsonProperty("username")
    private String username;
    @JsonProperty("name")
    private String name;
    @JsonProperty("profilePicture")
    private String profilePicture;
    private String firstName;
    private String lastName;
    private Date birthday;
    private String country;
    private String city;
    private int zipCode;
    private String streetName;
}
