package com.hivein.verificationservice.repository;

import com.hivein.verificationservice.model.entity.SecureToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SecureTokenRepository extends JpaRepository<SecureToken, Long> {
    Optional<SecureToken> findByEmail(String email);
}
