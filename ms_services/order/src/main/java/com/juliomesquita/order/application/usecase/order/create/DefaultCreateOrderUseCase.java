package com.juliomesquita.order.application.usecase.order.create;

import com.juliomesquita.order.application.usecase.order.retrive.getbyid.OrderOutput;
import com.juliomesquita.order.domain.OrderGateway;
import com.juliomesquita.order.domain.entities.Order;

import java.util.Objects;

public class DefaultCreateOrderUseCase extends CreateOrderUseCase {
    private final OrderGateway orderGateway;

    public DefaultCreateOrderUseCase(final OrderGateway orderGateway) {
        this.orderGateway = Objects.requireNonNull(orderGateway);
    }

    @Override
    public CreateOrderOutput execute(final CreateOrderCommand aCommand) {
        final Order anOrder = Order.newOrder(aCommand.tableId(), aCommand.products());
        return new CreateOrderOutput(this.orderGateway.save(anOrder));
    }
}
