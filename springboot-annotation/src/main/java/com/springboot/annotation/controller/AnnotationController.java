package com.springboot.annotation.controller;


import com.alibaba.fastjson.JSON;
import com.springboot.annotation.bean.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnnotationController {

    @Autowired
    private UserInfo userInfo;

    @RequestMapping("/getuserinfo")
    public String getUserInfo(){
       return   JSON.toJSONString(userInfo);
    }
}
