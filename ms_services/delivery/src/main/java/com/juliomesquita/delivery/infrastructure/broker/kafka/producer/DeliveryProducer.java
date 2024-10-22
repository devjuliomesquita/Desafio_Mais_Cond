package com.juliomesquita.delivery.infrastructure.broker.kafka.producer;

import com.juliomesquita.delivery.infrastructure.broker.kafka.consumer.CreateDeliveryMessage;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class DeliveryProducer {
    private final KafkaTemplate<String, CloseOrderMessage> kafkaTemplate;

    public DeliveryProducer(final KafkaTemplate<String, CloseOrderMessage> kafkaTemplate) {
        this.kafkaTemplate = Objects.requireNonNull(kafkaTemplate);
    }

    public void send(CloseOrderMessage data) {
        final Message<CloseOrderMessage> message = MessageBuilder
                .withPayload(data)
                .setHeader(KafkaHeaders.TOPIC, "delivery-topic")
                .build();
        this.kafkaTemplate.send(message);
        System.out.println(message);
    }
}
