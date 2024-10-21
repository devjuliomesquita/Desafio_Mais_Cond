package com.juliomesquita.order.application.usecase.order.changestatus;

import com.juliomesquita.order.domain.enums.OrderStatus;

public record ChangeStatusCommand(String orderId, OrderStatus status) {
}
