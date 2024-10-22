package com.juliomesquita.preparation.infrastructure.api.models;

import java.time.Instant;

public record ListComandasResponse(
        String id,
        boolean closed,
        Instant createdAt
) {
}
