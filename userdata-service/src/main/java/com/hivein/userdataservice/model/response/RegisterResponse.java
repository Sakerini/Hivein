package com.hivein.userdataservice.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class RegisterResponse {

    @JsonProperty("message")
    private String message;
}
