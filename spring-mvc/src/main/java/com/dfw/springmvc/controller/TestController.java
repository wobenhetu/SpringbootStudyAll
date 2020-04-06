package com.dfw.springmvc.controller;

import com.dfw.springmvc.annotation.MyController;
import com.dfw.springmvc.annotation.MyRequestMapping;
import com.dfw.springmvc.annotation.MyRequestParam;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@MyController
@MyRequestMapping("/test")
public class TestController {

	 @MyRequestMapping("/doTest")
    public void test1(HttpServletRequest request, HttpServletResponse response,
    		@MyRequestParam("param") String param){
 		System.out.println(param);
	    try {
            response.getWriter().write( "doTest method success! param:"+param);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	 
	 
	 @MyRequestMapping("/doTest2")
    public void test2(HttpServletRequest request, HttpServletResponse response){
        try {
            response.getWriter().println("doTest2 method success!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}