package com.springboot.feign.controller;


import com.springboot.feign.feign.TestFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeignController {

    @Autowired
    TestFeign testFeign;

    @GetMapping("/gethostname")
    public String echo(){
        return testFeign.echo3();
    }
}
