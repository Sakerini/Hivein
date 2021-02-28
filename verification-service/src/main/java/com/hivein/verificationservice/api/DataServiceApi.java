package com.hivein.verificationservice.api;

import com.hivein.verificationservice.model.response.BasicResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "${userData.service.name}", url = "${userData.service.base.url}")
public interface DataServiceApi {

    @PutMapping("/activate/{email}")
    BasicResponse activateEmail(@PathVariable(name = "email") String email);

    @PutMapping("/change-password/")
    BasicResponse changePassword(@RequestParam(name = "username") String username, @RequestParam(name = "password") String password);
}
