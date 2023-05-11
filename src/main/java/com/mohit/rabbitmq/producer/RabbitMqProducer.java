package com.mohit.rabbitmq.producer;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.mohit.rabbitmq.dto.User;

@Service
public class RabbitMqProducer {

	@Value("${rabbitmq.exchange.name}")
	private String exchange;

	@Value("${rabbitmq.routing.key}")
	private String routingKey;

	@Value("${rabbitmq.routing.json.key}")
	private String routingJsonKey;

	private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMqProducer.class);

	@Autowired
	private RabbitTemplate rabbitTemplate;

	public void sendMessage(String message) {
		LOGGER.info(String.format("Message sent -> %s", message));
		rabbitTemplate.convertAndSend(exchange, routingKey, message);
	}

	public void sendJsonMessage(User user) {
		
		user.setId(UUID.randomUUID().toString());
		LOGGER.info(String.format("Json message sent -> %s", user.toString()));
		rabbitTemplate.convertAndSend(exchange, routingJsonKey, user);
	}

}
