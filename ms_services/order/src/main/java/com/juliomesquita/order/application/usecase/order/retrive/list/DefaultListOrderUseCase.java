package com.juliomesquita.order.application.usecase.order.retrive.list;

import com.juliomesquita.order.domain.OrderGateway;

import java.util.List;
import java.util.Objects;

public class DefaultListOrderUseCase extends ListOrderUseCase {
    private final OrderGateway orderGateway;

    public DefaultListOrderUseCase(final OrderGateway orderGateway) {
        this.orderGateway = Objects.requireNonNull(orderGateway);
    }

    @Override
    public List<ListOrderOutput> execute() {
        return this.orderGateway
                .getAll()
                .stream()
                .map(ListOrderOutput::from)
                .toList();
    }
}
