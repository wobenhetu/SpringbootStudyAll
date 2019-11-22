package com.springboot.business.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;


@RefreshScope
@RestController
@RequestMapping("/getbussiness")
public class BussinessController {


    @GetMapping("/getinfo")
    public String getBussinessInfo() {

        /*try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        InetAddress ia = null;
        String returnString = "";
        try {
            ia = InetAddress.getLocalHost();
            String host = ia.getHostName();//获取计算机主机名
            String IP= ia.getHostAddress();//获取计算机IP
            returnString = "<h1>" + host+"  "+IP+ "</h1>";
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return returnString;
    }



    @Value("${username}")
    private String username;

    @Value("${userpwd}")
    private String userpwd;

    @GetMapping("/getconfiginfo")
    public String getConfigInfo() {
        return "配置信息:用户名："+username+"  密码："+userpwd;
    }
}
