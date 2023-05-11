package com.mohit.rabbitmq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mohit.rabbitmq.dto.User;
import com.mohit.rabbitmq.producer.RabbitMqProducer;

@RestController
@RequestMapping("/rabbitmq")
public class MessageController {

	@Autowired
	private RabbitMqProducer rabbitMqProducer;

//    public MessageController(RabbitMQProducer producer) {
//        this.producer = producer;
//    }

	// http://localhost:8080/rabbitmq/publish?message=hello
	@GetMapping("/publish")
	public ResponseEntity<String> sendMessage(@RequestParam("message") String message) {
		rabbitMqProducer.sendMessage(message);
		return ResponseEntity.ok("Message sent to RabbitMQ ...");
	}

	@PostMapping("/json/publish")
	public ResponseEntity<String> sendJsonMessage(@RequestBody User user) {
		rabbitMqProducer.sendJsonMessage(user);
		return ResponseEntity.ok("Json message sent to RabbitMQ ...");
	}
}
