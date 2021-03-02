package com.hivein.verificationservice.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum  VerificationStates {
    EMAIL("email"),
    PASSWORD("password");

    private String state;
}
