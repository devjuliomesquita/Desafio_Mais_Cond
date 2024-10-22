package com.juliomesquita.preparation.domain.entities;

import java.time.Instant;
import java.util.UUID;

public class Comanda {
    private String id;
    private String orderId;
    private boolean closed;
    private Instant createdAt;
    private Instant updatedAt;

    public static Comanda newComanda(final String anOrderId) {
        validate(anOrderId);
        final String anId = UUID.randomUUID().toString();
        final Instant now = Instant.now();
        return new Comanda(anId, anOrderId, false, now, now);
    }

    public Comanda closeComanda(){
        this.closed = true;
        this.updatedAt = Instant.now();
        return this;
    }

    private static void validate(final String anOrderId) {
        if (anOrderId == null) {
            throw new IllegalArgumentException("'Order id' should be not null");
        }

        if (anOrderId.isBlank()) {
            throw new IllegalArgumentException("'Order id' should be not blank");
        }
    }

    private Comanda(
            final String id,
            final String orderId,
            final boolean closed,
            final Instant createdAt,
            final Instant updatedAt
    ) {
        this.id = id;
        this.orderId = orderId;
        this.closed = closed;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getId() {
        return id;
    }

    public String getOrderId() {
        return orderId;
    }

    public boolean isClosed() {
        return closed;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}
