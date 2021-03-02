package com.hivein.gateway.config;


import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

public class GatewaySecurityConfiguration extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/auth/**")
                .permitAll()
                .antMatchers("/user/signup")
                .permitAll()
                .antMatchers("/**")
                .authenticated();
    }
}
