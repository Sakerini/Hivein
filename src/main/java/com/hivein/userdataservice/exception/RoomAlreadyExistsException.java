package com.hivein.userdataservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RoomAlreadyExistsException extends BaseException{

    public RoomAlreadyExistsException(String code, String message) {
        super(code, message);
    }
}
