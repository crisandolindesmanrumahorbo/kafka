package com.kafka.consumer.listener;

import com.kafka.consumer.model.User;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @KafkaListener(topics = "kafka-test", groupId = "group_id")
    public void consume(String message) {
        System.out.println("Consume message " + message);
    }

    @KafkaListener(topics = "kafka-test-json", groupId = "group_IDD_json", containerFactory = "userKafkaListenerContainerFactory")
    public void userConsume(@Payload User user, @Headers MessageHeaders headers) {
        System.out.println("Consume json user " + user.getName() + "\n Header: " + headers.toString());
    }

}
