package com.springboot.feign.feign;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class TestFeignFallCallBackFactory implements FallbackFactory<TestFeign> {
    @Override
    public TestFeign create(Throwable throwable) {
        return () -> "dfw=="+throwable.getMessage().toString();
    }
}
