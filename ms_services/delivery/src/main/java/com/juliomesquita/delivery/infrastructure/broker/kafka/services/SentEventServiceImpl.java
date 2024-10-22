package com.juliomesquita.delivery.infrastructure.broker.kafka.services;

import com.juliomesquita.delivery.infrastructure.broker.kafka.producer.CloseOrderMessage;
import com.juliomesquita.delivery.infrastructure.broker.kafka.producer.DeliveryProducer;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class SentEventServiceImpl {
    private final DeliveryProducer deliveryProducer;

    public SentEventServiceImpl(final DeliveryProducer deliveryProducer) {
        this.deliveryProducer = Objects.requireNonNull(deliveryProducer);
    }

    public void sentEvent(CloseOrderMessage data) {
        this.deliveryProducer.send(data);
    }
}
