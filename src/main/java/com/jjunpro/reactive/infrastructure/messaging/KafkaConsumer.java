package com.jjunpro.reactive.infrastructure.messaging;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @KafkaListener(topics = "${message.topic.name}", groupId = ConsumerConfig.GROUP_ID_CONFIG)
    public void consume(String message) {
        System.out.println(String.format("Consumed message : %s", message));
    }
}