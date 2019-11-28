package com.springboot.rabbitmq.consumer;

import com.springboot.rabbitmq.entity.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TopicConsumer {

    @RabbitListener(queues = {"topic.queue.one"})
    public void topicQueueOneConsumer(String message) {
        log.info("topic.queue.one -> {} ", message.toString());
    }

    @RabbitListener(queues = {"topic.queue.two"})
    public void topicQueueTwoConsumer(String message) {
        log.info("topic.queue.two -> {} ", message.toString());
    }
}