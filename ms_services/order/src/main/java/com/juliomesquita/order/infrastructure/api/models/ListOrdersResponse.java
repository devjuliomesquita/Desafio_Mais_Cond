package com.juliomesquita.order.infrastructure.api.models;

import java.time.Instant;

public record ListOrdersResponse(
        String id,
        String tableId,
        Instant createdAt,
        String status
) {
}
