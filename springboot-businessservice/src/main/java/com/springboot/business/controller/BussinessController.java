package com.springboot.business.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;


@RestController
@RequestMapping("/getbussiness")
public class BussinessController {


    @GetMapping("/getinfo")
    public String getBussinessInfo() {

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
