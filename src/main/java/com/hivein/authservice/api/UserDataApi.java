package com.hivein.authservice.api;

import com.hivein.authservice.exception.BaseException;
import com.hivein.authservice.model.response.LoginInfoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "${userdata.service.name}", url = "${userdata.service.base.url}")
public interface UserDataApi {

    @GetMapping("/admin/get-authinfo/{username}")
    LoginInfoResponse getLoginInfo(@PathVariable(name = "username") String username) throws BaseException;
}
