package com.springboot.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * 
 * @author maoshengchun
 * 2018-12-20
 */

@SpringBootApplication()
@MapperScan("com.springboot.mybatis.mapper")
public class App
{
	public static void main(String args[]) {
		SpringApplication.run(App.class, args);
	}
}
