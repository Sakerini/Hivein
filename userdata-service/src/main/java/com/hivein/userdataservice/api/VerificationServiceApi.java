package com.hivein.userdataservice.api;

import com.hivein.userdataservice.model.response.BaseResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "${verificationService.service.name}", url = "${verificationService.service.base.url}")
public interface VerificationServiceApi {

    @PostMapping("/send/email/{email}")
    BaseResponse sendVerificationEmail(@PathVariable(name = "email") String email);
}
