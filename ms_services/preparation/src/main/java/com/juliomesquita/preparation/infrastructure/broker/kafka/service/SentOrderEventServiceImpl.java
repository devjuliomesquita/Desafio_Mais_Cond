package com.juliomesquita.preparation.infrastructure.broker.kafka.service;

import com.juliomesquita.preparation.application.usecase.commom.UpdateOrderMessage;
import com.juliomesquita.preparation.domain.SentEventService;
import com.juliomesquita.preparation.infrastructure.broker.kafka.producer.PreparationOrderProducer;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class SentOrderEventServiceImpl
        implements SentEventService<UpdateOrderMessage> {
    private final PreparationOrderProducer preparationOrderProducer;

    public SentOrderEventServiceImpl(
            final PreparationOrderProducer preparationOrderProducer
    ) {
        this.preparationOrderProducer = Objects.requireNonNull(preparationOrderProducer);
    }

    @Override
    public void sentEvent(UpdateOrderMessage data) {
        this.preparationOrderProducer.send(data);
    }
}
