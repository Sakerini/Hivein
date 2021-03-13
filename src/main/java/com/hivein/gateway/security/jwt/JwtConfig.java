package com.hivein.gateway.security.jwt;

import com.hivein.gateway.security.filter.JwtTokenFilter;
import com.hivein.gateway.service.ResourceService;
import com.hivein.gateway.service.TokenVerificationService;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JwtConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private TokenVerificationService verificationService;
    private ResourceService resourceService;

    public JwtConfig(TokenVerificationService verificationService, ResourceService resourceService) {
        this.verificationService = verificationService;
        this.resourceService = resourceService;
    }

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        JwtTokenFilter jwtTokenFilter = new JwtTokenFilter(verificationService, resourceService);
        httpSecurity.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
