package com.hivein.verificationservice.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ErrorResponse extends BasicResponse {

    public ErrorResponse(String code, String message) {
        super(code, message);
    }
}
