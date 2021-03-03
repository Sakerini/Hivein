package com.hivein.authservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BadRequestException extends BaseException {
    public BadRequestException(String code, String message) {
        super(code, message);
    }
}
