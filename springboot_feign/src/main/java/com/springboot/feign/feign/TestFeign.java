package com.springboot.feign.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "test-service-a")
@Component
public interface TestFeign {

    @GetMapping(path = "/getbussiness/getinfo")
    public String echo3();
}