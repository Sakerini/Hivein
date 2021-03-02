package com.hivein.userdataservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EmailNotSentException extends BaseException {
    public EmailNotSentException(String code, String message) {
        super(code, message);
    }
}
