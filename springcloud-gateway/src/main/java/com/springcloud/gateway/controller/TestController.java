package com.springcloud.gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;


@RestController
public class TestController {

    @GetMapping(value = "getinfo")
    public String getInfo(){
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
}
