package com.juliomesquita.preparation.infrastructure.api.models;

import com.juliomesquita.preparation.domain.dto.OrderDTO;

import java.time.Instant;

public record ComandaResponse(
        String id,
        OrderDTO order,
        boolean closed,
        Instant createdAt,
        Instant updatedAt
) {
}
