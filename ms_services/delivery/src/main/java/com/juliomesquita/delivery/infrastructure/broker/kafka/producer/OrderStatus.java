package com.juliomesquita.delivery.infrastructure.broker.kafka.producer;

public enum OrderStatus {
    CREATED,
    PREPARED,
    SENT,
    COMPLETED;
}
