package com.springboot.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DirectConfig {

    @Bean
    public Queue directQueue() {
        return QueueBuilder.durable("direct.queue").build();
    }

    @Bean
    public DirectExchange directExchange() {
        return (DirectExchange) ExchangeBuilder.directExchange("direct.exchange").build();
    }

    @Bean
    public Binding directBinding() {
        return BindingBuilder.bind(directQueue()).to(directExchange()).withQueueName();
    }
}