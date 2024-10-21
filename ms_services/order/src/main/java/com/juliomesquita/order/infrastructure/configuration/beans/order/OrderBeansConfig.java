package com.juliomesquita.order.infrastructure.configuration.beans.order;

import com.juliomesquita.order.application.usecase.order.changestatus.ChangeStatusOrderUseCase;
import com.juliomesquita.order.application.usecase.order.changestatus.DefaultChangeStatusOrderUseCase;
import com.juliomesquita.order.application.usecase.order.create.CreateOrderUseCase;
import com.juliomesquita.order.application.usecase.order.create.DefaultCreateOrderUseCase;
import com.juliomesquita.order.application.usecase.order.retrive.getbyid.DefaultGetOrderByIdUseCase;
import com.juliomesquita.order.application.usecase.order.retrive.getbyid.GetOrderByIdUseCase;
import com.juliomesquita.order.application.usecase.order.retrive.list.DefaultListOrderUseCase;
import com.juliomesquita.order.application.usecase.order.retrive.list.ListOrderUseCase;
import com.juliomesquita.order.domain.OrderGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

@Configuration
public class OrderBeansConfig {
    private final OrderGateway orderGateway;

    public OrderBeansConfig(final OrderGateway orderGateway) {
        this.orderGateway = Objects.requireNonNull(orderGateway);
    }

    @Bean
    public CreateOrderUseCase createOrderUseCase() {
        return new DefaultCreateOrderUseCase(orderGateway);
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
