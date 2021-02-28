package com.hivein.verificationservice.api;

import com.hivein.verificationservice.model.response.BasicResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "${userData.service.name}", url = "${userData.service.base.url}")
public interface DataServiceApi {

    @PutMapping("/activate/{email}")
    BasicResponse activateEmail(@PathVariable(name = "email") String email);
}
