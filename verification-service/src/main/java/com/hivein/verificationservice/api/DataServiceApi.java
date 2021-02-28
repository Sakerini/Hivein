package com.hivein.verificationservice.api;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "${userData.service.name}", url = "${userData.service.base.url}")
public interface DataServiceApi {

}
