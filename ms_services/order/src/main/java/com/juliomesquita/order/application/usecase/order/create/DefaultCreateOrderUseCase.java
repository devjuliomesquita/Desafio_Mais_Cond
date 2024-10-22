package com.juliomesquita.order.application.usecase.order.create;

import com.juliomesquita.order.domain.OrderGateway;
import com.juliomesquita.order.domain.SentEventService;
import com.juliomesquita.order.domain.entities.Order;

import java.util.Objects;

public class DefaultCreateOrderUseCase extends CreateOrderUseCase {
    private final OrderGateway orderGateway;
    private final SentEventService<CreateComandaMessage> sentEventService;

    public DefaultCreateOrderUseCase(
            final OrderGateway orderGateway,
            final SentEventService<CreateComandaMessage> sentEventService
    ) {
        this.orderGateway = Objects.requireNonNull(orderGateway);
        this.sentEventService = Objects.requireNonNull(sentEventService);

    }

    @Override
    public CreateOrderOutput execute(final CreateOrderCommand aCommand) {
        final Order anOrder = Order.newOrder(aCommand.tableId(), aCommand.products());
        this.sentEventService.sentEvent(new CreateComandaMessage(anOrder.getId()));
        return new CreateOrderOutput(this.orderGateway.save(anOrder));
    }
}
