package com.hivein.authservice.service.impl;

import com.hivein.authservice.api.UserDataApi;
import com.hivein.authservice.exception.BaseException;
import com.hivein.authservice.model.dto.RoleDTO;
import com.hivein.authservice.model.entity.Authority;
import com.hivein.authservice.model.entity.UserLoginInfo;
import com.hivein.authservice.model.response.LoginInfoResponse;
import com.hivein.authservice.service.UserDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
public class UserDataServiceImpl implements UserDataService {

    private final UserDataApi userDataApi;

    @Autowired
    public UserDataServiceImpl(UserDataApi userDataApi) {
        this.userDataApi = userDataApi;
    }

    @Override
    public UserLoginInfo getLoginUserData(String username) throws BaseException {
        log.info("Inside UserDataService get login user data...");

        LoginInfoResponse response = userDataApi.getLoginInfo(username);
        Set<Authority> authorities = new HashSet<>();

        for (RoleDTO role: response.getRoles()) {
            authorities.add(new Authority(role.getUsername(), role.getRole()));
        }

        return new UserLoginInfo(
                response.getUsername(),
                response.getPassword(),
                response.isActive(),
                authorities
        );
    }
}
