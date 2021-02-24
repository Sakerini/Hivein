package com.hivein.authservice.model.response;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class TokenResponse {

    @NonNull
    private String accessToken;
    private String tokenType = "Bearer";
}
