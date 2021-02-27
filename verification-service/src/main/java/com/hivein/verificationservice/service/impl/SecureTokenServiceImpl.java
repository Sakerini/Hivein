package com.hivein.verificationservice.service.impl;

import com.hivein.verificationservice.model.entity.SecureToken;
import com.hivein.verificationservice.repository.SecureTokenRepository;
import com.hivein.verificationservice.service.SecureTokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class SecureTokenServiceImpl implements SecureTokenService {

    private final SecureTokenRepository secureTokenRepository;

    @Autowired
    public SecureTokenServiceImpl(SecureTokenRepository secureTokenRepository) {
        this.secureTokenRepository = secureTokenRepository;
    }

    @Override
    public List<SecureToken> findAllTokens() {
        return secureTokenRepository.findAll();
    }

    @Override
    public Optional<SecureToken> findTokenByEmail(String email) {
        return secureTokenRepository.findByEmail(email);
    }

    @Override
    public SecureToken saveToken(SecureToken token) {
        return secureTokenRepository.save(token);
    }
}
