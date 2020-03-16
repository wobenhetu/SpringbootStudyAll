package com.springboot.feign.feign;

import com.alibaba.fastjson.JSONObject;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * Feign请求拦截器
 **/
public class FeignBasicAuthRequestInterceptor  implements RequestInterceptor {

    public static String URL = "http://192.168.101.123:8876/oauth/token?grant_type=client_credentials&scope=all&client_id=deng&client_secret=deng";

    public FeignBasicAuthRequestInterceptor() {
 
    }

    @Override
    public void apply(RequestTemplate template) {
        template.header("token", getToken());
    }


    public String getToken(){
        RestTemplate restTemplate = new RestTemplate();
        //创建请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(headers);
        ResponseEntity<String> exchange = restTemplate.postForEntity(URL, entity, String.class);
        System.out.println("dfw=="+exchange.getBody());

        JSONObject jsonObject = JSONObject.parseObject(exchange.getBody().toString());
        String access_token = jsonObject.getString("access_token");
        return access_token;
    }
}