package com.yss.fregata.server.config.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;


@RefreshScope
@RestController
public class BussinessController {

    @Value("${username}")
    private String username;

    @Value("${userpwd}")
    private String userpwd;

    @GetMapping("/getconfiginfo")
    public String getConfigInfo() {
        return "配置信息:用户名："+username+"  密码："+userpwd;
    }
}
