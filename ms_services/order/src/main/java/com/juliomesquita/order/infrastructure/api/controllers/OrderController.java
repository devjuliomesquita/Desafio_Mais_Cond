package com.juliomesquita.order.infrastructure.api.controllers;

import com.juliomesquita.order.application.usecase.order.retrive.getbyid.GetOrderByIdUseCase;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class OrderController {
    private final GetOrderByIdUseCase getOrderByIdUseCase;

    public OrderController(final GetOrderByIdUseCase getOrderByIdUseCase) {
        this.getOrderByIdUseCase = Objects.requireNonNull(getOrderByIdUseCase);
    }
}
