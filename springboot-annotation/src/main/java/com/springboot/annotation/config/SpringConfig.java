package com.springboot.annotation.config;

import com.springboot.annotation.bean.UserInfo;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/*
  @ConfigurationProperties(prefix = "spring.user")
* 读取配置文件，并使用configurationproperties映射到实体里面
  如果一个JavaBean中大量属性值要和配置文件进行映射，可以使用@ConfigurationProperties；
 * */

@Configuration
@PropertySource(value = {"classpath:userInfo.properties"}, encoding = "utf-8")
public class SpringConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.user")
    public UserInfo userInfo() {
        return new UserInfo();
    }
}