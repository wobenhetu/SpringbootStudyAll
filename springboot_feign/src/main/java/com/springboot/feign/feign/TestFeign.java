package com.springboot.feign.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
// 直接访问服务
//@FeignClient(name = "test-service-a",configuration = FooConfiguration.class,
//        fallbackFactory = TestFeignFallCallBackFactory.class)

//通过网关访问服务
@FeignClient(name = "springcloud-gateway",configuration = FooConfiguration.class,
        fallbackFactory = TestFeignFallCallBackFactory.class)
@Component
public interface TestFeign {

    @GetMapping(path = "/getbussiness/getinfo")
    public String echo3();

}