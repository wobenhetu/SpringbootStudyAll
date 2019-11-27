package com.springboot.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
* Fanout Exchange 消息广播的模式，不管路由键或者是路由模式，会把消息发给绑定给它的全部队列，
* 如果配置了 routing_key 会被忽略。
* */
@Configuration
public class FanoutConfig {

    @Bean
    public Queue fanoutQueueOne() {
        return QueueBuilder.durable("fanout.queue.one").build();
    }

    @Bean
    public Queue fanoutQueueTwo() {
        return QueueBuilder.durable("fanout.queue.two").build();
    }

    @Bean
    public Queue fanoutQueueThree() {
        return QueueBuilder.durable("fanout.queue.three").build();
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        return (FanoutExchange) ExchangeBuilder.fanoutExchange("fanout.exchange").build();
    }

    @Bean
    public Binding fanoutBindingOne() {
        return BindingBuilder.bind(fanoutQueueOne()).to(fanoutExchange());
    }

    @Bean
    public Binding fanoutBindingTwo() {
        return BindingBuilder.bind(fanoutQueueTwo()).to(fanoutExchange());
    }

    @Bean
    public Binding fanoutBindingThree() {
        return BindingBuilder.bind(fanoutQueueThree()).to(fanoutExchange());
    }
}