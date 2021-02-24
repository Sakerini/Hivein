package com.sakerini.hiveinauthservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EmailAlreadyExistsException extends BaseException {

    public EmailAlreadyExistsException(String code, String message) {
        super(code, message);
    }
}
