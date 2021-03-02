package com.hivein.userdataservice.util;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusCodes {
    OK("code-200"),
    BAD_REQUEST("code-400"),
    UNAUTHORIZED("code-401"),
    FORBIDDEN("code-403"),
    NOT_FOUND("code-404"),
    REQUEST_TIMEOUT("code-408"),
    CONFLICT("code-409"),
    INTERNAL_ERROR("code-500");

    private String code;
}
