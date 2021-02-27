package com.hivein.userdataservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UsernameAlreadyExistsException extends BaseException{

    public UsernameAlreadyExistsException(String code, String message) {
        super(code, message);
    }
}
