package com.juliomesquita.menu.infrastruture.broker.kafka.producer;

import com.juliomesquita.menu.application.usecases.table.addproducts.CreateOrderMessage;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class MenuProducer {
    private final KafkaTemplate<String, CreateOrderMessage> kafkaTemplate;

    public MenuProducer(final KafkaTemplate<String, CreateOrderMessage> kafkaTemplate) {
        this.kafkaTemplate = Objects.requireNonNull(kafkaTemplate);
    }

    public void send(CreateOrderMessage data) {
        final Message<CreateOrderMessage> message = MessageBuilder
                .withPayload(data)
                .setHeader(KafkaHeaders.TOPIC, "menu-topic")
                .build();
        this.kafkaTemplate.send(message);
        System.out.println(message);
    }
}
