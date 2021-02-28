package com.hivein.verificationservice.service.impl;

import com.hivein.verificationservice.api.DataServiceApi;
import com.hivein.verificationservice.service.DataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DataServiceImpl implements DataService {

    private final DataServiceApi dataServiceApi;

    @Autowired
    public DataServiceImpl(DataServiceApi dataServiceApi) {
        this.dataServiceApi = dataServiceApi;
    }

    @Override
    public void activateEmail(String email) {
        dataServiceApi.activateEmail(email);
    }
}
