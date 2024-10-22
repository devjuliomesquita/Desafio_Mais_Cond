package com.juliomesquita.preparation.application.usecase.create;

import com.juliomesquita.preparation.domain.ComandaGateway;
import com.juliomesquita.preparation.domain.entities.Comanda;

import java.util.Objects;

public class DefaultCreateComandaUseCase extends CreateComandaUseCase{
    private final ComandaGateway comandaGateway;

    public DefaultCreateComandaUseCase(final ComandaGateway comandaGateway) {
        this.comandaGateway = Objects.requireNonNull(comandaGateway);
    }

    @Override
    public CreateComandaOutput execute(CreateComandaCommand aCommand) {
        //verificar se esse pedido realmente existe
        final Comanda aComanda = Comanda.newComanda(aCommand.anOrderId());
        return new CreateComandaOutput(this.comandaGateway.save(aComanda));
    }
}
