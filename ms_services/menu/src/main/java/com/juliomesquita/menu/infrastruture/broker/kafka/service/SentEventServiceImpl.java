package com.juliomesquita.menu.infrastruture.broker.kafka.service;

import com.juliomesquita.menu.application.usecases.table.addproducts.CreateOrderMessage;
import com.juliomesquita.menu.domain.commom.SentEventService;
import com.juliomesquita.menu.infrastruture.broker.kafka.producer.MenuProducer;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Service
public class SentEventServiceImpl implements SentEventService<CreateOrderMessage> {
    private final MenuProducer menuProducer;

    public SentEventServiceImpl(final MenuProducer menuProducer) {
        this.menuProducer = Objects.requireNonNull(menuProducer);
    }

    @Override
    public void sentEvent(CreateOrderMessage data) {
        this.menuProducer.send(data);
    }
}
