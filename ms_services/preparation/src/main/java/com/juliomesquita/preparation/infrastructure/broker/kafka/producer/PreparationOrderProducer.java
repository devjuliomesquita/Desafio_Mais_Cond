package com.juliomesquita.preparation.infrastructure.broker.kafka.producer;

import com.juliomesquita.preparation.application.usecase.commom.UpdateOrderMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class PreparationOrderProducer {
    private static final Logger log = LoggerFactory.getLogger(PreparationOrderProducer.class);
    private final KafkaTemplate<String, UpdateOrderMessage> kafkaTemplate;

    public PreparationOrderProducer(final KafkaTemplate<String, UpdateOrderMessage> kafkaTemplate) {
        this.kafkaTemplate = Objects.requireNonNull(kafkaTemplate);
    }

    public void send(UpdateOrderMessage data) {
        log.info("Objeto que será enviado :: {}", data);
        final Message<UpdateOrderMessage> message = MessageBuilder
                .withPayload(data)
                .setHeader(KafkaHeaders.TOPIC, "preparation-order-topic")
                .build();
        this.kafkaTemplate.send(message);
        log.info("Mensagem enviada :: {}", message);
    }
}
