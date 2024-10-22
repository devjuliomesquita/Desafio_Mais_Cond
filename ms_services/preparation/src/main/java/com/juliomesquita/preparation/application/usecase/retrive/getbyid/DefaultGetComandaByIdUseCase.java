package com.juliomesquita.preparation.application.usecase.retrive.getbyid;

import com.juliomesquita.preparation.domain.ComandaGateway;
import com.juliomesquita.preparation.domain.OrderService;
import com.juliomesquita.preparation.domain.dto.OrderDTO;
import com.juliomesquita.preparation.domain.entities.Comanda;

import java.util.Objects;

public class DefaultGetComandaByIdUseCase extends GetComandaByIdUseCase {
    private final ComandaGateway comandaGateway;
    private final OrderService orderService;

    public DefaultGetComandaByIdUseCase(
            final ComandaGateway comandaGateway,
            final OrderService orderService
    ) {
        this.comandaGateway = Objects.requireNonNull(comandaGateway);
        this.orderService = Objects.requireNonNull(orderService);
    }

    @Override
    public GetComandaByIdOutput execute(final String anId) {
        final Comanda aComanda = this.comandaGateway.getById(anId)
                .orElseThrow(() -> new RuntimeException("'Comanda' with id :: %s not found".formatted(anId)));
        final OrderDTO anOrder = this.orderService.findOrderById(aComanda.getOrderId());
        return GetComandaByIdOutput.from(aComanda, anOrder);
    }
}
