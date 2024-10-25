package com.juliomesquita.order.infrastructure.broker.kafka.producer;

import com.juliomesquita.order.application.usecase.order.create.CreateComandaMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class OrderProducer {
    private static final Logger log = LoggerFactory.getLogger(OrderProducer.class);
    private final KafkaTemplate<String, CreateComandaMessage> kafkaTemplate;

    public OrderProducer(final KafkaTemplate<String, CreateComandaMessage> kafkaTemplate) {
        this.kafkaTemplate = Objects.requireNonNull(kafkaTemplate);
    }

    public void send(CreateComandaMessage data) {
        log.info("Objeto que será enviado :: {}", data);
        final Message<CreateComandaMessage> message = MessageBuilder
                .withPayload(data)
                .setHeader(KafkaHeaders.TOPIC, "order-topic")
                .build();
        this.kafkaTemplate.send(message);
        log.info("Mensagem enviada :: {}", message);
    }
}
