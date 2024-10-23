package com.juliomesquita.order.domain.entities;

import com.juliomesquita.order.domain.enums.OrderStatus;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

public class Order {
    private String id;
    private String tableId;
    private Map<String, Integer> products;
    private Instant createdAt;
    private Instant updatedAt;
    private OrderStatus status;

    public static Order with(
            String id,
            String tableId,
            Map<String, Integer> products,
            Instant createdAt,
            Instant updatedAt,
            OrderStatus status
    ) {
        return new Order(id, tableId, products, createdAt, updatedAt, status);
    }

    public static Order newOrder(
            final String aTableId,
            final Map<String, Integer> aProducts
    ) {
        validate(aTableId, aProducts);
        final String anId = UUID.randomUUID().toString();
        final Instant anInstant = Instant.now();
        return new Order(anId, aTableId, aProducts, anInstant, anInstant, OrderStatus.CREATED);
    }

    public Order changeStatus(final OrderStatus aStatus) {
        this.updatedAt = Instant.now();
        this.status = aStatus;
        return this;
    }

    private static void validate(final String aTableId, final Map<String, Integer> aProducts) {
        if (aTableId == null) {
            throw new IllegalArgumentException("'Table id' should be not null");
        }

        if (aTableId.isBlank()) {
            throw new IllegalArgumentException("'Table id' should be not blank");
        }

        if (aProducts.size() <= 0) {
            throw new IllegalArgumentException("Quantity of products must be greater than zero");
        }
        for (String productId : aProducts.keySet()) {
            if (aProducts.get(productId) <= 0) {
                throw new IllegalArgumentException("Quantity of product must be greater than zero");
            }
        }
    }

    private Order(
            final String id,
            final String tableId,
            final Map<String, Integer> products,
            final Instant createdAt,
            final Instant updatedAt,
            final OrderStatus status
    ) {
        this.id = id;
        this.tableId = tableId;
        this.products = products;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getTableId() {
        return tableId;
    }

    public Map<String, Integer> getProducts() {
        return products;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public OrderStatus getStatus() {
        return status;
    }
}
