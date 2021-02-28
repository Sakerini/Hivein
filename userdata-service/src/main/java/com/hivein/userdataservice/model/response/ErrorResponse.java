package com.hivein.userdataservice.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse extends BaseResponse {

    public ErrorResponse(String code, String message) {
        super(code, message);
    }
}
