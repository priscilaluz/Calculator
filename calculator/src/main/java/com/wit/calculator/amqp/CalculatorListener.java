package com.wit.calculator.amqp;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.wit.calculator.service.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.concurrent.TimeoutException;

@Component
@Slf4j
@RequiredArgsConstructor
public class CalculatorListener {
	private static final String QUEUE = "calculator_queue";
	private static ConnectionFactory factory = new ConnectionFactory();

	@RabbitListener(queues = QUEUE)
	public void listenerQueue(final Message<?> message) throws IOException, TimeoutException {
		String payload = new String((byte[]) message.getPayload());
		String correlationId = message.getHeaders().get("amqp_correlationId").toString();
		String queueReply = message.getHeaders().get("amqp_replyTo").toString();

		log.info(String.format("CorrelationId %s --> Requesting : %s, QueueReply: %s ", correlationId, message, queueReply));

		String returnValue = calculation(payload);

		AMQP.BasicProperties prop = new AMQP.BasicProperties.Builder().correlationId(correlationId).build();

		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		channel.basicPublish("", queueReply, prop, returnValue.getBytes());
	}
	private String calculation(String payload) {
		try {
			String[] payloadArray = payload.split(",");
			Operation operation = (Operation) Class.forName("com.wit.calculator.service.impl."+payloadArray[2])
				.getDeclaredConstructor().newInstance();
			BigDecimal a = new BigDecimal(payloadArray[0]);
			BigDecimal b = new BigDecimal(payloadArray[1]);
			return operation.calculation(a, b).toString();
		} catch (Exception e) {
			return String.format("ERROR: %s", e.getMessage());
		}
	}
}
