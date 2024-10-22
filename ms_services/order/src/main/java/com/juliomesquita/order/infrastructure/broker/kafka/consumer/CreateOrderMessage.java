package com.juliomesquita.order.infrastructure.broker.kafka.consumer;

import java.util.Map;

public record CreateOrderMessage(String tableId, Map<String, Integer> products) {
}
