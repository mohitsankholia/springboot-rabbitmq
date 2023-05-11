package com.mohit.rabbitmq.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.mohit.rabbitmq.dto.User;

@Service
public class RabbitmqConsumer {

	private static final Logger LOGGER = LoggerFactory.getLogger(RabbitmqConsumer.class);

	@RabbitListener(queues = { "${rabbitmq.queue.name}" })
	public void consume(String message) {
		LOGGER.info(String.format("Received message -> %s", message));
	}

	@RabbitListener(queues = { "${rabbitmq.queue.json.name}" })
	public void consumeJsonMessage(User user) {
		LOGGER.info(String.format("Received JSON message -> %s", user.toString()));
	}

}
