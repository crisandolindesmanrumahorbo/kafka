package com.producer.kafka.controller;

import com.producer.kafka.model.User;
import com.producer.kafka.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("kafka")
public class UserController {

    UserService userService = new UserService();
    @Autowired
    private KafkaTemplate<String, User> kafkaTemplate;

    @GetMapping("/publish/{username}")
    public String post(@PathVariable("username") String username) {
        Message<User> message = userService.buildMessage(username);
        kafkaTemplate.send(message);
        return "Success to publish " + username;
    }
}
