package com.juliomesquita.preparation.application.usecase.commom;

public record UpdateOrderMessage(String orderId, OrderStatus status) {
}
