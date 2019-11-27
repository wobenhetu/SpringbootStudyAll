package com.springboot.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
* headers 也是根据规则匹配, 相较于 direct 和 topic 固定地使用 routing_key ,
* headers 则是一个自定义匹配规则的类型. 在队列与交换器绑定时, 会设定一组键值对规则,
* 消息中也包括一组键值对( headers 属性),
* 当这些键值对有一对, 或全部匹配时, 消息被投送到对应队列.*/
@Configuration
public class HeadersConfig {

    @Bean
    public Queue headersQueue() {
        return QueueBuilder.durable("headers.queue").build();
    }

    @Bean
    public HeadersExchange headersExchange() {
        return (HeadersExchange) ExchangeBuilder.headersExchange("headers.exchange").build();
    }

    @Bean
    public Binding headersBinding() {
        return BindingBuilder.bind(headersQueue()).to(headersExchange()).where("headers-key1").exists();
    }
}