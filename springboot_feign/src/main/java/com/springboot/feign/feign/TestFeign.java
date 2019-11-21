package com.springboot.feign.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "test-service-a",configuration = FooConfiguration.class,fallbackFactory = TestFeignFallCallBackFactory.class)
@Component
public interface TestFeign {

    @GetMapping(path = "/getbussiness/getinfo")
    public String echo3();

}