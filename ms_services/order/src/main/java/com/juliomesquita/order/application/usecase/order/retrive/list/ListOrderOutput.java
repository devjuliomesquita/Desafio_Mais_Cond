package com.juliomesquita.order.application.usecase.order.retrive.list;

import com.juliomesquita.order.domain.entities.Order;
import com.juliomesquita.order.domain.enums.OrderStatus;

import java.time.Instant;

public record ListOrderOutput(
        String id,
        String tableId,
        Instant createdAt,
        OrderStatus status
) {
    public static ListOrderOutput from(final Order anOrder) {
        return new ListOrderOutput(
                anOrder.getId(),
                anOrder.getTableId(),
                anOrder.getCreatedAt(),
                anOrder.getStatus()
        );
    }
}
