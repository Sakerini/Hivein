package com.hivein.userdataservice.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomCreationResponse extends BaseResponse {
    public RoomCreationResponse(String code, String message) {
        super(code, message);
    }
}
