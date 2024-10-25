package com.juliomesquita.order.infrastructure.broker.kafka.consumer;

import com.juliomesquita.order.application.usecase.order.changestatus.ChangeStatusCommand;
import com.juliomesquita.order.application.usecase.order.changestatus.ChangeStatusOrderUseCase;
import com.juliomesquita.order.application.usecase.order.create.CreateOrderCommand;
import com.juliomesquita.order.application.usecase.order.create.CreateOrderUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class OrderConsumer {
    private static final Logger log = LoggerFactory.getLogger(OrderConsumer.class);
    private final CreateOrderUseCase createOrderUseCase;
    private final ChangeStatusOrderUseCase changeStatusOrderUseCase;

    public OrderConsumer(
            final CreateOrderUseCase createOrderUseCase,
            final ChangeStatusOrderUseCase changeStatusOrderUseCase
    ) {
        this.createOrderUseCase = Objects.requireNonNull(createOrderUseCase);
        this.changeStatusOrderUseCase = Objects.requireNonNull(changeStatusOrderUseCase);
    }

    @KafkaListener(topics = "menu-topic")
    public void execute(final CreateOrderMessage anEvent) {
        log.info("Mensagem recebida :: {}, criação de pedido", anEvent);
        this.createOrderUseCase.execute(new CreateOrderCommand(anEvent.tableId(), anEvent.products()));
    }

    @KafkaListener(topics = "preparation-order-topic")
    public void execute(final UpdateOrderMessage anEvent){
        log.info("Mensagem recebida :: {}, atualização de pedido", anEvent);
        this.changeStatusOrderUseCase.execute(new ChangeStatusCommand(anEvent.orderId(), anEvent.status()));
    }

    @KafkaListener(topics = "delivery-topic")
    public void execute(final CloseOrderMessage anEvent){
        log.info("Mensagem recebida :: {}, , atualização de pedido", anEvent);
        this.changeStatusOrderUseCase.execute(new ChangeStatusCommand(anEvent.orderId(), anEvent.status()));
    }
}
