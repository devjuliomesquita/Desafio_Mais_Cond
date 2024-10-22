package com.juliomesquita.preparation.infrastructure.broker.kafka.producer;

import com.juliomesquita.preparation.application.usecase.commom.UpdateOrderMessage;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class PreparationOrderProducer {
    private final KafkaTemplate<String, UpdateOrderMessage> kafkaTemplate;

    public PreparationOrderProducer(final KafkaTemplate<String, UpdateOrderMessage> kafkaTemplate) {
        this.kafkaTemplate = Objects.requireNonNull(kafkaTemplate);
    }

    public void send(UpdateOrderMessage data) {
        final Message<UpdateOrderMessage> message = MessageBuilder
                .withPayload(data)
                .setHeader(KafkaHeaders.TOPIC, "preparation-order-topic")
                .build();
        this.kafkaTemplate.send(message);
        System.out.println(message);
    }
}
