package com.juliomesquita.order.application.usecase.order.create;

import java.util.Map;

public record CreateOrderCommand(String tableId, Map<String, Integer> products) {
}
