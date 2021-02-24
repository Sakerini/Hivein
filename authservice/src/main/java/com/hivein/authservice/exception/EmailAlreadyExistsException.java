package com.hivein.authservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EmailAlreadyExistsException extends BaseException {

    public EmailAlreadyExistsException(String code, String message) {
        super(code, message);
    }
}
