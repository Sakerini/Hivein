package com.hivein.userdataservice.service.impl;

import com.hivein.userdataservice.api.VerificationServiceApi;
import com.hivein.userdataservice.exception.EmailNotSentException;
import com.hivein.userdataservice.model.response.BaseResponse;
import com.hivein.userdataservice.service.VerificationService;
import com.hivein.userdataservice.util.StatusCodes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class VerificationServiceImpl implements VerificationService {

    private final VerificationServiceApi serviceApi;

    @Autowired
    public VerificationServiceImpl(VerificationServiceApi serviceApi) {
        this.serviceApi = serviceApi;
    }

    @Override
    public void sendVerificationEmail(String greetingName, String email) throws EmailNotSentException {
        BaseResponse response = serviceApi.sendVerificationEmail(greetingName, email);
        if (!response.getCode().equals(StatusCodes.OK.getCode())){
            throw new EmailNotSentException(StatusCodes.INTERNAL_ERROR.getCode() , "Email not sent");
        }
    }
}
