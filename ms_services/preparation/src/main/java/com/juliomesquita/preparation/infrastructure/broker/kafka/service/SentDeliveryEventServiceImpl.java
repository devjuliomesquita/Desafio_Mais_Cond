package com.juliomesquita.preparation.infrastructure.broker.kafka.service;

import com.juliomesquita.preparation.application.usecase.close.CreateDeliveryMessage;
import com.juliomesquita.preparation.domain.SentEventService;
import com.juliomesquita.preparation.infrastructure.broker.kafka.producer.PreparationProducer;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class SentDeliveryEventServiceImpl implements
        SentEventService<CreateDeliveryMessage> {
    private final PreparationProducer preparationProducer;

    public SentDeliveryEventServiceImpl(final PreparationProducer preparationProducer) {
        this.preparationProducer = Objects.requireNonNull(preparationProducer);
    }

    @Override
    public void sentEvent(CreateDeliveryMessage data) {
        this.preparationProducer.send(data);
    }
}
