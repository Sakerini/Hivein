package com.hivein.gateway.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "${auth.service.name}", url = "${auth.service.base.url}")
public interface AuthServiceApi {

    @GetMapping("/auth/validate-token")
    ResponseEntity<?> validateToken(@RequestParam(name = "token") String token);

    @GetMapping("/auth/user-info")
    UserDetails getUserDetails(@RequestParam(name = "username") String username);
}
