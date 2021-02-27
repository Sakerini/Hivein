package com.hivein.verificationservice.service;

import com.hivein.verificationservice.model.entity.SecureToken;

import java.util.List;
import java.util.Optional;

public interface SecureTokenService {
    List<SecureToken> findAllTokens();
    Optional<SecureToken> findTokenByEmail(String email);
    SecureToken saveToken(SecureToken token);

}
