package com.juliomesquita.order.infrastructure.broker.kafka.service;

import com.juliomesquita.order.application.usecase.order.create.CreateComandaMessage;
import com.juliomesquita.order.domain.SentEventService;
import com.juliomesquita.order.infrastructure.broker.kafka.producer.OrderProducer;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class SentEventServiceImpl implements SentEventService<CreateComandaMessage> {
    private final OrderProducer orderProducer;

    public SentEventServiceImpl(final OrderProducer orderProducer) {
        this.orderProducer = Objects.requireNonNull(orderProducer);
    }

    @Override
    public void sentEvent(CreateComandaMessage data) {
        this.orderProducer.send(data);
    }
}
