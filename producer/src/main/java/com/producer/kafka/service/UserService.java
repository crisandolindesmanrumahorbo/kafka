package com.producer.kafka.service;

import com.producer.kafka.model.User;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private static final String TOPIC = "kafka-test-json";

    public Message<User> buildMessage(String username) {
        User user = new User(username, "Information Technology", 120000);
        return MessageBuilder
                .withPayload(user)
                .setHeader(KafkaHeaders.TOPIC, TOPIC)
                .build();
    }
}
