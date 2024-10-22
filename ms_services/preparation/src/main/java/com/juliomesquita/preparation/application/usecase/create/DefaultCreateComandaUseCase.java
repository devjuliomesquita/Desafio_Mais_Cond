package com.juliomesquita.preparation.application.usecase.create;

import com.juliomesquita.preparation.domain.ComandaGateway;
import com.juliomesquita.preparation.domain.OrderService;
import com.juliomesquita.preparation.domain.entities.Comanda;

import java.util.Objects;

public class DefaultCreateComandaUseCase extends CreateComandaUseCase {
    private final ComandaGateway comandaGateway;
    private final OrderService orderService;

    public DefaultCreateComandaUseCase(
            final ComandaGateway comandaGateway,
            final OrderService orderService
    ) {
        this.comandaGateway = Objects.requireNonNull(comandaGateway);
        this.orderService = Objects.requireNonNull(orderService);
    }

    @Override
    public CreateComandaOutput execute(CreateComandaCommand aCommand) {
        if (this.orderService.existOrderById(aCommand.anOrderId())) {
            throw new RuntimeException("'Order' with id :: %s not found".formatted(aCommand.anOrderId()));
        }
        final Comanda aComanda = Comanda.newComanda(aCommand.anOrderId());
        return new CreateComandaOutput(this.comandaGateway.save(aComanda));
    }
}
