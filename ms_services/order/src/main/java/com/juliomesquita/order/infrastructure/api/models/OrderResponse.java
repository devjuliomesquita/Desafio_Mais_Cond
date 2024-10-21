package com.juliomesquita.order.infrastructure.api.models;

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
