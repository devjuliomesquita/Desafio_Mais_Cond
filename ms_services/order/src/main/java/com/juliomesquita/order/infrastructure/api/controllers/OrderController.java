package com.juliomesquita.order.infrastructure.api.controllers;

import com.juliomesquita.order.application.usecase.order.retrive.getbyid.GetOrderByIdUseCase;
import com.juliomesquita.order.application.usecase.order.retrive.list.ListOrderUseCase;
import com.juliomesquita.order.infrastructure.api.OrderAPI;
import com.juliomesquita.order.infrastructure.api.models.ListOrdersResponse;
import com.juliomesquita.order.infrastructure.api.models.OrderResponse;
import com.juliomesquita.order.infrastructure.api.presenters.OrderPresenter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
public class OrderController implements OrderAPI {
    private final GetOrderByIdUseCase getOrderByIdUseCase;
    private final ListOrderUseCase listOrderUseCase;

    public OrderController(
            final GetOrderByIdUseCase getOrderByIdUseCase,
            final ListOrderUseCase listOrderUseCase
    ) {
        this.getOrderByIdUseCase = Objects.requireNonNull(getOrderByIdUseCase);
        this.listOrderUseCase = Objects.requireNonNull(listOrderUseCase);
    }

    @Override
    public ResponseEntity<List<ListOrdersResponse>> listOrders() {
        final List<ListOrdersResponse> response = this.listOrderUseCase.execute()
                .stream()
                .map(OrderPresenter.presenterList)
                .toList();
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<OrderResponse> getOrderById(final String id) {
        final OrderResponse response = OrderPresenter.presenterSimple
                .compose(this.getOrderByIdUseCase::execute)
                .apply(id);
        return ResponseEntity.ok(response);
    }
}
