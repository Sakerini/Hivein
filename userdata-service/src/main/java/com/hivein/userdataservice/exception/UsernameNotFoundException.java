package com.hivein.userdataservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UsernameNotFoundException extends BaseException {
    public UsernameNotFoundException(String code, String message) {
        super(code, message);
    }

}
