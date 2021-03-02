package com.hivein.userdataservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EmailNotFoundException extends BaseException {
    public EmailNotFoundException(String code, String message) {
        super(code, message);
    }

}
