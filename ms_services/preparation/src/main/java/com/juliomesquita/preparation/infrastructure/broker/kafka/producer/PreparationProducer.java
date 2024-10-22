package com.juliomesquita.preparation.infrastructure.broker.kafka.producer;

import com.juliomesquita.preparation.application.usecase.close.CreateDeliveryMessage;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class PreparationProducer {
    private final KafkaTemplate<String, CreateDeliveryMessage> kafkaTemplate;

    public PreparationProducer(final KafkaTemplate<String, CreateDeliveryMessage> kafkaTemplate) {
        this.kafkaTemplate = Objects.requireNonNull(kafkaTemplate);
    }

    public void send(CreateDeliveryMessage data) {
        final Message<CreateDeliveryMessage> message = MessageBuilder
                .withPayload(data)
                .setHeader(KafkaHeaders.TOPIC, "preparation-topic")
                .build();
        this.kafkaTemplate.send(message);
        System.out.println(message);
    }
}
