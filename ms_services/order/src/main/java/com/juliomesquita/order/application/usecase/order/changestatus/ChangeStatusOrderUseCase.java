package com.juliomesquita.order.application.usecase.order.changestatus;

public abstract class ChangeStatusOrderUseCase {
    public abstract void execute(ChangeStatusCommand aCommand);
}
