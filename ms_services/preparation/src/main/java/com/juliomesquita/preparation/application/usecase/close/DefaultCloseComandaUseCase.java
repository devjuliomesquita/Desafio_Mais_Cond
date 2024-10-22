package com.juliomesquita.preparation.application.usecase.close;

import com.juliomesquita.preparation.domain.ComandaGateway;
import com.juliomesquita.preparation.domain.entities.Comanda;

import java.util.Objects;

public class DefaultCloseComandaUseCase extends CloseComandaUseCase{
    private final ComandaGateway comandaGateway;

    public DefaultCloseComandaUseCase(final ComandaGateway comandaGateway) {
        this.comandaGateway = Objects.requireNonNull(comandaGateway);
    }

    @Override
    public void execute(CloseComandaCommand aCommand) {
        final Comanda aComanda = this.comandaGateway.getById(aCommand.id())
                .orElseThrow(() -> new RuntimeException("'Comanda' with id :: %s not found.".formatted(aCommand)));
        aComanda.closeComanda();
        this.comandaGateway.save(aComanda);
    }
}
