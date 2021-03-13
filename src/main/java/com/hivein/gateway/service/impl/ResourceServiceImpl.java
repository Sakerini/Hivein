package com.hivein.gateway.service.impl;

import com.hivein.gateway.api.UserDataApi;
import com.hivein.gateway.model.JwtUser;
import com.hivein.gateway.model.response.AuthorizationInfoResponse;
import com.hivein.gateway.model.response.RoleDTO;
import com.hivein.gateway.service.ResourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
public class ResourceServiceImpl implements ResourceService {

    private final UserDataApi userDataApi;

    @Autowired
    public ResourceServiceImpl(UserDataApi userDataApi) {
        this.userDataApi = userDataApi;
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
