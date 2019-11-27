package com.springboot.rabbitmq.consumer;

import com.springboot.rabbitmq.entity.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author lazycece
 * @date 2019/04/04
 */
@Component
@Slf4j
public class HeadersConsumer {

    @RabbitListener(queues = {"headers.queue"})
    public void headersQueueConsumer(Message message) {
        log.info("headers.queue.one -> {} ", message.toString());
    }
}