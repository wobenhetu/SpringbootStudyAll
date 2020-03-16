package com.springboot.feign.feign;


import org.springframework.stereotype.Component;

@Component
public class TestFeignFallCallBack implements TestFeign{
    @Override
    public String echo3() {
        return "oh,进fallcallback了，访问的服务出现异常";
    }
}
