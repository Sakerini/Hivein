package com.hivein.authservice.service.impl;

import com.hivein.authservice.exception.BaseException;
import com.hivein.authservice.model.AuthUserDetails;
import com.hivein.authservice.model.entity.UserLoginInfo;
import com.hivein.authservice.service.UserDataService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthUserDetailsService implements UserDetailsService {

    private final UserDataService userDataService;

    public AuthUserDetailsService(UserDataService userDataService) {
        this.userDataService = userDataService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserLoginInfo user;
        try {
            user = userDataService.getLoginUserData(username);
        } catch (BaseException ex) {
            throw new UsernameNotFoundException("Username not found");
        }
        return new AuthUserDetails(user);
    }
}
