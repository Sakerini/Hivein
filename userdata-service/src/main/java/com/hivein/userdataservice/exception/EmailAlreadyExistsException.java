package com.hivein.userdataservice.exception;

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
