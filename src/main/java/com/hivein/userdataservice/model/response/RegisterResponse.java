package com.hivein.userdataservice.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterResponse extends BaseResponse {

    public RegisterResponse(String code, String message) {
        super(code, message);
    }
}
