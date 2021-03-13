package com.hivein.gateway.api;


import com.hivein.gateway.model.response.AuthorizationInfoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "${userData.service.name}", url = "${userData.service.base.url}")
public interface UserDataApi {

    @GetMapping("/admin/get-authinfo/{username}")
    AuthorizationInfoResponse getLoginInfo(@PathVariable(name = "username") String username);
}
