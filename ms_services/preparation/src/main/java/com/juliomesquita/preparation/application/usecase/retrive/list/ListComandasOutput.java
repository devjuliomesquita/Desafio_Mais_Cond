package com.juliomesquita.preparation.application.usecase.retrive.list;

import com.juliomesquita.preparation.domain.entities.Comanda;

import java.time.Instant;

public record ListComandasOutput(
        String id,
        boolean closed,
        Instant createdAt
) {
    public static ListComandasOutput from(final Comanda aComanda) {
        return new ListComandasOutput(aComanda.getId(), aComanda.isClosed(), aComanda.getCreatedAt());
    }
}
