package com.juliomesquita.preparation.infrastructure.broker.kafka.producer;

import com.juliomesquita.preparation.application.usecase.close.CreateDeliveryMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class PreparationProducer {
    private static final Logger log = LoggerFactory.getLogger(PreparationProducer.class);
    private final KafkaTemplate<String, CreateDeliveryMessage> kafkaTemplate;

    public PreparationProducer(final KafkaTemplate<String, CreateDeliveryMessage> kafkaTemplate) {
        this.kafkaTemplate = Objects.requireNonNull(kafkaTemplate);
    }

    public void send(CreateDeliveryMessage data) {
        log.info("Objeto que será enviado :: {}", data);
        final Message<CreateDeliveryMessage> message = MessageBuilder
                .withPayload(data)
                .setHeader(KafkaHeaders.TOPIC, "preparation-topic")
                .build();
        this.kafkaTemplate.send(message);
        log.info("Mensagem que será enviada :: {}", message);
    }
}
