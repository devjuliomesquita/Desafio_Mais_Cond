package com.juliomesquita.preparation.infrastructure.broker.kafka.consumer;

import com.juliomesquita.preparation.application.usecase.create.CreateComandaCommand;
import com.juliomesquita.preparation.application.usecase.create.CreateComandaUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class PreparationConsumer {
    private static final Logger log = LoggerFactory.getLogger(PreparationConsumer.class);
    private final CreateComandaUseCase createComandaUseCase;

    public PreparationConsumer(final CreateComandaUseCase createComandaUseCase) {
        this.createComandaUseCase = Objects.requireNonNull(createComandaUseCase);
    }

    @KafkaListener(topics = "order-topic")
    public void execute(final CreateComandaMessage anEvent) {
        log.info("Mensagem recebida :: {}, criação de comanda", anEvent);
        this.createComandaUseCase.execute(new CreateComandaCommand(anEvent.orderId()));
    }
}
