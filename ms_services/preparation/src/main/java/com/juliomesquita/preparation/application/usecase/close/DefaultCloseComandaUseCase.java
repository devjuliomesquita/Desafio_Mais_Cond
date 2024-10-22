package com.juliomesquita.preparation.application.usecase.close;

import com.juliomesquita.preparation.application.usecase.commom.OrderStatus;
import com.juliomesquita.preparation.application.usecase.commom.UpdateOrderMessage;
import com.juliomesquita.preparation.domain.ComandaGateway;
import com.juliomesquita.preparation.domain.SentEventService;
import com.juliomesquita.preparation.domain.entities.Comanda;

import java.util.Objects;

public class DefaultCloseComandaUseCase extends CloseComandaUseCase {
    private final ComandaGateway comandaGateway;
    private final SentEventService<UpdateOrderMessage> sentEventServiceOrder;
    private final SentEventService<CreateDeliveryMessage> sentEventServiceDelivery;

    public DefaultCloseComandaUseCase(
            final ComandaGateway comandaGateway,
            final SentEventService<UpdateOrderMessage> sentEventServiceOrder,
            final SentEventService<CreateDeliveryMessage> sentEventServiceDelivery
    ) {
        this.comandaGateway = Objects.requireNonNull(comandaGateway);
        this.sentEventServiceOrder = Objects.requireNonNull(sentEventServiceOrder);
        this.sentEventServiceDelivery = Objects.requireNonNull(sentEventServiceDelivery);
    }

    @Override
    public void execute(CloseComandaCommand aCommand) {
        final Comanda aComanda = this.comandaGateway.getById(aCommand.id())
                .orElseThrow(() -> new RuntimeException("'Comanda' with id :: %s not found.".formatted(aCommand)));
        aComanda.closeComanda();
        this.sentEventServiceOrder.sentEvent(new UpdateOrderMessage(aComanda.getOrderId(), OrderStatus.SENT));
        this.sentEventServiceDelivery.sentEvent(new CreateDeliveryMessage(aComanda.getOrderId()));
        this.comandaGateway.save(aComanda);
    }
}
