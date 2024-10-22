package com.juliomesquita.order.infrastructure.broker.kafka.consumer;

import com.juliomesquita.order.domain.enums.OrderStatus;

public record CloseOrderMessage(String orderId, OrderStatus status) {
}
