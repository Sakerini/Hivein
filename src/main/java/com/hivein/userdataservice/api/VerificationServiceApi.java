package com.hivein.userdataservice.api;

import com.hivein.userdataservice.model.response.BaseResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "${verificationService.service.name}", url = "${verificationService.service.base.url}")
public interface VerificationServiceApi {

    @PostMapping("/send/email/")
    BaseResponse sendVerificationEmail(
            @RequestParam(name = "name") String name, @RequestParam(name = "email") String email);
}
