package com.hivein.userdataservice.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hivein.userdataservice.model.dto.RoleDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
public class AuthInformationResponse {

    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;

    @JsonProperty("active")
    private boolean active;

    @JsonProperty("roles")
    private Set<RoleDTO> roles;
}
