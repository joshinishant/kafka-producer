package com.kafka.producer.controller;

import com.google.gson.Gson;
import com.kafka.producer.Entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("kafka")
public class RestController {

    @Autowired
    KafkaTemplate<String, Book> kafkaTemplate;

    @PostMapping("/publish/{topic}")
    public String publishMessage(@PathVariable("topic") String topic, @RequestBody() String message){

        Gson gson=new Gson();
        Book book=gson.fromJson(message,Book.class);

        kafkaTemplate.send(topic,book);

        return "Message published successfully";
    }

}
