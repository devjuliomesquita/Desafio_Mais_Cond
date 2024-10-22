package com.juliomesquita.preparation.domain.dto;

import java.time.Instant;
import java.util.List;

public record OrderDTO(
        String id,
        String tableId,
        List<ProductDTO> products,
        Instant createdAt,
        Instant updatedAt,
        String status
) {
}
