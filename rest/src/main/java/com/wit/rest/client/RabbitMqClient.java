package com.wit.rest.client;

import com.rabbitmq.client.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.io.IOException;
import com.rabbitmq.client.AMQP.BasicProperties;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeoutException;

@Slf4j
@Component
public class RabbitMqClient {
    private static final String QUEUE = "calculator_queue";
    private static ConnectionFactory factory = new ConnectionFactory();

    public String send(String message) throws IOException, TimeoutException, InterruptedException {
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        String queueReply = channel.queueDeclare().getQueue();
        String correlationId = UUID.randomUUID().toString();
        log.info(String.format("CorrelationId %s --> Requesting : %s, QueueReply: %s ", correlationId, message, queueReply));

        BasicProperties prop = new BasicProperties.Builder().correlationId(correlationId).replyTo(queueReply).build();
        channel.basicPublish("", QUEUE, prop, message.getBytes(StandardCharsets.UTF_8));

        final BlockingQueue<String> response = new ArrayBlockingQueue<String>(1);
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            if (delivery.getProperties().getCorrelationId().equals(correlationId)) {
                String messageReceived = new String(delivery.getBody(), StandardCharsets.UTF_8);
                log.info(String.format("Queue: %s CorrelationId %s --> Received : %s ",queueReply, correlationId, messageReceived));
                response.offer(messageReceived);
            }
        };
        channel.basicConsume(queueReply, true, deliverCallback, consumerTag -> { });
        return response.take();
    }

}
