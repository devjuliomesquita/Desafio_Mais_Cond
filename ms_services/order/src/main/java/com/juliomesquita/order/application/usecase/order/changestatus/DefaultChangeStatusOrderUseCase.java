package com.juliomesquita.order.application.usecase.order.changestatus;

import com.juliomesquita.order.domain.OrderGateway;
import com.juliomesquita.order.domain.entities.Order;

import java.util.Objects;

public class DefaultChangeStatusOrderUseCase extends ChangeStatusOrderUseCase {
    private final OrderGateway orderGateway;

    public DefaultChangeStatusOrderUseCase(final OrderGateway orderGateway) {
        this.orderGateway = Objects.requireNonNull(orderGateway);
    }

    @Override
    public void execute(ChangeStatusCommand aCommand) {
        final Order anOrder = this.orderGateway.getById(aCommand.orderId())
                .orElseThrow(() -> new RuntimeException("'Order' with id :: %s not found".formatted(aCommand)));
        anOrder.changeStatus(aCommand.status());
        this.orderGateway.save(anOrder);
    }
}
