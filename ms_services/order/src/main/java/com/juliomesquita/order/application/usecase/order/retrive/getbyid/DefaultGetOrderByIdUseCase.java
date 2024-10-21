package com.juliomesquita.order.application.usecase.order.retrive.getbyid;

import com.juliomesquita.order.domain.OrderGateway;

import java.util.Objects;

public class DefaultGetOrderByIdUseCase extends GetOrderByIdUseCase {
    private final OrderGateway orderGateway;

    public DefaultGetOrderByIdUseCase(final OrderGateway orderGateway) {
        this.orderGateway = Objects.requireNonNull(orderGateway);
    }

    @Override
    public OrderOutput execute(String aCommand) {
        return this.orderGateway.getById(aCommand)
                .map(OrderOutput::from)
                .orElseThrow(() -> new RuntimeException("'Order' with id :: %s not found".formatted(aCommand)));
    }
}
