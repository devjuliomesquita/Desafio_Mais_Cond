package com.juliomesquita.order.infrastructure.broker.kafka.consumer;

import com.juliomesquita.order.domain.enums.OrderStatus;

public record UpdateOrderMessage(String orderId, OrderStatus status) {
}
