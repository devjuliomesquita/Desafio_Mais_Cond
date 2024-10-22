package com.juliomesquita.preparation.infrastructure.configurations.beans.comanda;

import com.juliomesquita.preparation.application.usecase.close.CloseComandaUseCase;
import com.juliomesquita.preparation.application.usecase.close.CreateDeliveryMessage;
import com.juliomesquita.preparation.application.usecase.close.DefaultCloseComandaUseCase;
import com.juliomesquita.preparation.application.usecase.commom.UpdateOrderMessage;
import com.juliomesquita.preparation.application.usecase.create.CreateComandaUseCase;
import com.juliomesquita.preparation.application.usecase.create.DefaultCreateComandaUseCase;
import com.juliomesquita.preparation.application.usecase.retrive.getbyid.DefaultGetComandaByIdUseCase;
import com.juliomesquita.preparation.application.usecase.retrive.getbyid.GetComandaByIdUseCase;
import com.juliomesquita.preparation.application.usecase.retrive.list.DefaultListComandasUseCase;
import com.juliomesquita.preparation.application.usecase.retrive.list.ListComandasUseCase;
import com.juliomesquita.preparation.domain.ComandaGateway;
import com.juliomesquita.preparation.domain.OrderService;
import com.juliomesquita.preparation.domain.SentEventService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

@Configuration
public class ComandaBeansConfig {
    private final ComandaGateway comandaGateway;
    private final OrderService orderService;
    private final SentEventService<UpdateOrderMessage> sentOrderEventService;
    private final SentEventService<CreateDeliveryMessage> sentDeliveryEventService;

    public ComandaBeansConfig(
            final ComandaGateway comandaGateway,
            final OrderService orderService,
            final SentEventService<UpdateOrderMessage> sentOrderEventService,
            final SentEventService<CreateDeliveryMessage> sentDeliveryEventService
    ) {
        this.comandaGateway = Objects.requireNonNull(comandaGateway);
        this.orderService = Objects.requireNonNull(orderService);
        this.sentOrderEventService = Objects.requireNonNull(sentOrderEventService);
        this.sentDeliveryEventService = Objects.requireNonNull(sentDeliveryEventService);
    }

    @Bean
    public CreateComandaUseCase createComandaUseCase() {
        return new DefaultCreateComandaUseCase(comandaGateway, orderService, sentOrderEventService);
    }

    @Bean
    public GetComandaByIdUseCase getComandaByIdUseCase() {
        return new DefaultGetComandaByIdUseCase(comandaGateway, orderService);
    }

    @Bean
    public ListComandasUseCase listComandasUseCase() {
        return new DefaultListComandasUseCase(comandaGateway);
    }

    @Bean
    public CloseComandaUseCase closeComandaUseCase() {
        return new DefaultCloseComandaUseCase(comandaGateway, sentOrderEventService, sentDeliveryEventService);
    }
}
