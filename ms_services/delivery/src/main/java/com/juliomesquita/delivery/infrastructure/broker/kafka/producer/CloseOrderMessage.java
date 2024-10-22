package com.juliomesquita.delivery.infrastructure.broker.kafka.producer;

public record CloseOrderMessage(String orderId, OrderStatus status) {
}
