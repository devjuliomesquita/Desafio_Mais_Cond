package com.juliomesquita.order.application.usecase.order.retrive.getbyid;

import com.juliomesquita.order.domain.entities.Order;
import com.juliomesquita.order.domain.enums.OrderStatus;

import java.time.Instant;
import java.util.Map;

public record OrderOutput(
        String id,
        String tableId,
        Map<String, Integer> products,
        Instant createdAt,
        Instant updatedAt,
        OrderStatus status
) {
    public static OrderOutput from(final Order anOrder) {
        return new OrderOutput(
                anOrder.getId(),
                anOrder.getTableId(),
                anOrder.getProducts(),
                anOrder.getCreatedAt(),
                anOrder.getUpdatedAt(),
                anOrder.getStatus()
        );
    }
}
