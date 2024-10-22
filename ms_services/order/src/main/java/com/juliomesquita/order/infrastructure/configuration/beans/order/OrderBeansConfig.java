package com.juliomesquita.order.infrastructure.configuration.beans.order;

import com.juliomesquita.order.application.usecase.order.changestatus.ChangeStatusOrderUseCase;
import com.juliomesquita.order.application.usecase.order.changestatus.DefaultChangeStatusOrderUseCase;
import com.juliomesquita.order.application.usecase.order.create.CreateComandaMessage;
import com.juliomesquita.order.application.usecase.order.create.CreateOrderUseCase;
import com.juliomesquita.order.application.usecase.order.create.DefaultCreateOrderUseCase;
import com.juliomesquita.order.application.usecase.order.retrive.getbyid.DefaultGetOrderByIdUseCase;
import com.juliomesquita.order.application.usecase.order.retrive.getbyid.GetOrderByIdUseCase;
import com.juliomesquita.order.application.usecase.order.retrive.list.DefaultListOrderUseCase;
import com.juliomesquita.order.application.usecase.order.retrive.list.ListOrderUseCase;
import com.juliomesquita.order.domain.OrderGateway;
import com.juliomesquita.order.domain.SentEventService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

@Configuration
public class OrderBeansConfig {
    private final OrderGateway orderGateway;
    private final SentEventService<CreateComandaMessage> sentEventService;

    public OrderBeansConfig(
            final OrderGateway orderGateway,
            final SentEventService<CreateComandaMessage> sentEventService
    ) {
        this.orderGateway = Objects.requireNonNull(orderGateway);
        this.sentEventService = Objects.requireNonNull(sentEventService);
    }

    @Bean
    public CreateOrderUseCase createOrderUseCase() {
        return new DefaultCreateOrderUseCase(orderGateway, sentEventService);
    }

    @Bean
    public GetOrderByIdUseCase getOrderByIdUseCase() {
        return new DefaultGetOrderByIdUseCase(orderGateway);
    }

    @Bean
    public ListOrderUseCase listOrderUseCase() {
        return new DefaultListOrderUseCase(orderGateway);
    }

    @Bean
    public ChangeStatusOrderUseCase changeStatusOrderUseCase() {
        return new DefaultChangeStatusOrderUseCase(orderGateway);
    }
}
