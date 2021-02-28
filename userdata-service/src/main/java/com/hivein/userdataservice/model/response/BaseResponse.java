package com.hivein.userdataservice.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BaseResponse {

    @JsonProperty("code")
    private String code;

    @JsonProperty("message")
    private String message;
}
