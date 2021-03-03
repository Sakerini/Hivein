package com.hivein.authservice.service;

import com.hivein.authservice.exception.BaseException;
import com.hivein.authservice.model.entity.UserLoginInfo;

public interface UserDataService {
    UserLoginInfo getLoginUserData(String username) throws BaseException;
}
