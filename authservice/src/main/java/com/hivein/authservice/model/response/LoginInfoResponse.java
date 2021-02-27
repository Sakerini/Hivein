package com.hivein.authservice.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hivein.authservice.model.dto.RoleDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class LoginInfoResponse {

    @JsonProperty("username")
    private String username;
    @JsonProperty("password")
    private String password;
    @JsonProperty("active")
    private boolean active;
    @JsonProperty("roles")
    private Set<RoleDTO> roles;
}