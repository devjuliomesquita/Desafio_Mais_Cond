package com.juliomesquita.order.application.usecase.order.create;

public abstract  class CreateOrderUseCase {
    public abstract CreateOrderOutput execute(CreateOrderCommand aCommand);
}
