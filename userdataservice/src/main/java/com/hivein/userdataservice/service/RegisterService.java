package com.hivein.userdataservice.service;

import com.hivein.userdataservice.exception.BaseException;
import com.hivein.userdataservice.model.entity.Authority;
import com.hivein.userdataservice.model.entity.User;

public interface RegisterService {
    void registerUser(User user, Authority role) throws BaseException;
}
