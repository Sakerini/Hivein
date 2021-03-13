package com.hivein.gateway.security.jwt;

import com.hivein.gateway.security.filter.JwtTokenFilter;
import com.hivein.gateway.service.AuthService;
import com.hivein.gateway.service.TokenVerificationService;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JwtConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private TokenVerificationService verificationService;
    private AuthService authService;

    public JwtConfig(TokenVerificationService verificationService, AuthService authService) {
        this.verificationService = verificationService;
        this.authService = authService;
    }

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        JwtTokenFilter jwtTokenFilter = new JwtTokenFilter(verificationService, authService);
        httpSecurity.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
