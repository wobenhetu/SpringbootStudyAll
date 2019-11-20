package com.springboot.feign.feign;


import org.springframework.stereotype.Component;

@Component
public class TestFeignFallCallBack implements TestFeign{
    @Override
    public String echo3() {
        return "oh，进fallback了";
    }
}
