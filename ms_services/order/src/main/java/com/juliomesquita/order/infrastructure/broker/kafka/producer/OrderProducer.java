package com.juliomesquita.order.infrastructure.broker.kafka.producer;

import com.juliomesquita.order.application.usecase.order.create.CreateComandaMessage;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class OrderProducer {
    private final KafkaTemplate<String, CreateComandaMessage> kafkaTemplate;

    public OrderProducer(final KafkaTemplate<String, CreateComandaMessage> kafkaTemplate) {
        this.kafkaTemplate = Objects.requireNonNull(kafkaTemplate);
    }

    public void send(CreateComandaMessage data) {
        System.out.println(data);
        final Message<CreateComandaMessage> message = MessageBuilder
                .withPayload(data)
                .setHeader(KafkaHeaders.TOPIC, "order-topic")
                .build();
        this.kafkaTemplate.send(message);
        System.out.println(message);
    }
}
