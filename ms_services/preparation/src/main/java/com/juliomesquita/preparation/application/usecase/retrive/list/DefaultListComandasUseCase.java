package com.juliomesquita.preparation.application.usecase.retrive.list;

import com.juliomesquita.preparation.domain.ComandaGateway;

import java.util.List;
import java.util.Objects;

public class DefaultListComandasUseCase extends ListComandasUseCase {
    private final ComandaGateway comandaGateway;

    public DefaultListComandasUseCase(final ComandaGateway comandaGateway) {
        this.comandaGateway = Objects.requireNonNull(comandaGateway);
    }

    @Override
    public List<ListComandasOutput> execute() {
        return this.comandaGateway.getAll()
                .stream()
                .map(ListComandasOutput::from)
                .toList();
    }
}
