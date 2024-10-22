package com.juliomesquita.preparation.infrastructure.services.order;

import com.juliomesquita.preparation.domain.OrderService;
import com.juliomesquita.preparation.domain.dto.OrderDTO;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderClient orderClient;

    public OrderServiceImpl(final OrderClient orderClient) {
        this.orderClient = Objects.requireNonNull(orderClient);
    }

    @Override
    public boolean existOrderById(final String anOrderId) {
        return this.orderClient.findOrderById(anOrderId).isEmpty();
    }

    @Override
    public OrderDTO findOrderById(String anOrderId) {
        return this.orderClient.findOrderById(anOrderId)
                .orElseThrow(() -> new RuntimeException("'Order' with id :: %s not found".formatted(anOrderId)));
    }
}
