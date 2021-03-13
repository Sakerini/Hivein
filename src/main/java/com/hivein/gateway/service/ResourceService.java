package com.hivein.gateway.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface ResourceService {

    UserDetails getUserDetails(String username);
}
