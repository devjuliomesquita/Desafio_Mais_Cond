package com.juliomesquita.order.infrastructure.broker.kafka.consumer;

import com.juliomesquita.order.application.usecase.order.create.CreateOrderCommand;
import com.juliomesquita.order.application.usecase.order.create.CreateOrderUseCase;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class OrderConsumer {
    private final CreateOrderUseCase createOrderUseCase;

    public OrderConsumer(final CreateOrderUseCase createOrderUseCase) {
        this.createOrderUseCase = Objects.requireNonNull(createOrderUseCase);
    }

    @KafkaListener(topics = "menu-topic")
    public void execute(final CreateOrderMessage anEvent) {
        System.out.println("chegou no kafka listener :: %s".formatted(anEvent));
        this.createOrderUseCase.execute(new CreateOrderCommand(anEvent.tableId(), anEvent.products()));
    }
}
