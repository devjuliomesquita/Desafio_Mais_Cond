package com.juliomesquita.preparation.application.usecase.create;

import com.juliomesquita.preparation.application.usecase.commom.OrderStatus;
import com.juliomesquita.preparation.application.usecase.commom.UpdateOrderMessage;
import com.juliomesquita.preparation.domain.ComandaGateway;
import com.juliomesquita.preparation.domain.OrderService;
import com.juliomesquita.preparation.domain.SentEventService;
import com.juliomesquita.preparation.domain.entities.Comanda;

import java.util.Objects;

public class DefaultCreateComandaUseCase extends CreateComandaUseCase {
    private final ComandaGateway comandaGateway;
    private final OrderService orderService;
    private final SentEventService<UpdateOrderMessage> sentEventService;

    public DefaultCreateComandaUseCase(
            final ComandaGateway comandaGateway,
            final OrderService orderService,
            final SentEventService<UpdateOrderMessage> sentEventService
    ) {
        this.comandaGateway = Objects.requireNonNull(comandaGateway);
        this.orderService = Objects.requireNonNull(orderService);
        this.sentEventService = Objects.requireNonNull(sentEventService);
    }

    @Override
    public CreateComandaOutput execute(CreateComandaCommand aCommand) {
        if (this.orderService.existOrderById(aCommand.anOrderId())) {
            throw new RuntimeException("'Order' with id :: %s not found".formatted(aCommand.anOrderId()));
        }
        final Comanda aComanda = Comanda.newComanda(aCommand.anOrderId());

        this.sentEventService.sentEvent(new UpdateOrderMessage(aCommand.anOrderId(), OrderStatus.PREPARED));
        return new CreateComandaOutput(this.comandaGateway.save(aComanda));
    }
}
