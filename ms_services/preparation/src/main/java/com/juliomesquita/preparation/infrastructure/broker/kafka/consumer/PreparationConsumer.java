package com.juliomesquita.preparation.infrastructure.broker.kafka.consumer;

import com.juliomesquita.preparation.application.usecase.create.CreateComandaCommand;
import com.juliomesquita.preparation.application.usecase.create.CreateComandaUseCase;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class PreparationConsumer {
    private final CreateComandaUseCase createComandaUseCase;

    public PreparationConsumer(final CreateComandaUseCase createComandaUseCase) {
        this.createComandaUseCase = Objects.requireNonNull(createComandaUseCase);
    }

    @KafkaListener(topics = "order-topic")
    public void execute(final CreateComandaMessage anEvent) {
        System.out.println("chegou no kafka listener :: %s".formatted(anEvent));
        this.createComandaUseCase.execute(new CreateComandaCommand(anEvent.orderId()));
    }
}
