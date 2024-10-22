package com.juliomesquita.preparation.infrastructure.services.order.models;

import java.time.Instant;
import java.util.List;

public record OrderResponse(
        String id,
        String tableId,
        List<ProductsModel> products,
        Instant createdAt,
        Instant updatedAt,
        String status
) {
}
