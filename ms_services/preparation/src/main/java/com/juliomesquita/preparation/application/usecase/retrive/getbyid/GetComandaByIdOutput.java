package com.juliomesquita.preparation.application.usecase.retrive.getbyid;

import com.juliomesquita.preparation.domain.dto.OrderDTO;
import com.juliomesquita.preparation.domain.entities.Comanda;

import java.time.Instant;

public record GetComandaByIdOutput(
        String id,
        OrderDTO order,
        boolean closed,
        Instant createdAt,
        Instant updatedAt
) {
    public static GetComandaByIdOutput from(final Comanda aComanda, OrderDTO anOrder) {
        return new GetComandaByIdOutput(
                aComanda.getId(),
                anOrder,
                aComanda.isClosed(),
                aComanda.getCreatedAt(),
                aComanda.getUpdatedAt());
    }
}
