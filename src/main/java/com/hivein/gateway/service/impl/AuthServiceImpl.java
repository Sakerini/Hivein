package com.hivein.gateway.service.impl;

import com.hivein.gateway.api.AuthServiceApi;
import com.hivein.gateway.api.UserDataApi;
import com.hivein.gateway.model.JwtUser;
import com.hivein.gateway.model.response.AuthorizationInfoResponse;
import com.hivein.gateway.model.response.RoleDTO;
import com.hivein.gateway.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

    private final AuthServiceApi authServiceApi;
    private final UserDataApi userDataApi;

    @Autowired
    public AuthServiceImpl(AuthServiceApi authServiceApi, UserDataApi userDataApi) {
        this.authServiceApi = authServiceApi;
        this.userDataApi = userDataApi;
    }

    @Override
    public boolean validateToken(String token) {
        ResponseEntity<?> response = authServiceApi.validateToken(token);
        if (response.getStatusCode().value() == 200) {
            return true;
        }
        return false;
    }

    @Override
    public UserDetails getUserDetails(String username) {
        AuthorizationInfoResponse userInfo = userDataApi.getLoginInfo(username);
        Set<GrantedAuthority> authorities = new HashSet<>();
        for (RoleDTO authority : userInfo.getRoles()) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + authority.getRole()));
        }
        UserDetails userDetails = new JwtUser(username, userInfo.getPassword(), userInfo.isActive(), authorities);
        return userDetails;
    }
}
