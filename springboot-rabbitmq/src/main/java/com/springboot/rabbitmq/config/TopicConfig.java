package com.springboot.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
Topic Exchange主要有两种通配符：# 和 *

*（星号）：可以（只能）匹配一个单词
#（井号）：可以匹配多个单词（或者零个）


* */

@Configuration
public class TopicConfig {

    @Bean
    public Queue topicQueueOne() {
        return QueueBuilder.durable("topic.queue.one").build();
    }

    @Bean
    public Queue topicQueueTwo() {
        return QueueBuilder.durable("topic.queue.two").build();
    }

    @Bean
    public TopicExchange topicExchange() {
        return (TopicExchange) ExchangeBuilder.topicExchange("exchange-stomp").build();
    }

    @Bean
    public Binding topicBindingOne() {
        return BindingBuilder.bind(topicQueueOne()).to(topicExchange()).with("shopping.discount");
    }

    @Bean
    public Binding topicBindingTwo() {
        return BindingBuilder.bind(topicQueueTwo()).to(topicExchange()).with("#");
    }
}