package com.juliomesquita.preparation.infrastructure.comanda.persistence;

import com.juliomesquita.preparation.domain.entities.Comanda;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Instant;

@Table(name = "tb_comandas")
@Entity
public class ComandaEntity {
    @Id
    @Column(name = "comanda_id", nullable = false)
    private String id;

    @Column(name = "comanda_order_id", nullable = false)
    private String orderId;

    @Column(name = "comanda_closed", nullable = false)
    private boolean closed;

    @Column(name = "comanda_created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "comanda_updated_at", nullable = false)
    private Instant updatedAt;

    public static ComandaEntity from(final Comanda aComanda) {
        return new ComandaEntity(
                aComanda.getId(),
                aComanda.getOrderId(),
                aComanda.isClosed(),
                aComanda.getCreatedAt(),
                aComanda.getUpdatedAt()
        );
    }

    public static Comanda toAggregate(final ComandaEntity anEntity){
        return Comanda.with(
                anEntity.getId(),
                anEntity.getOrderId(),
                anEntity.isClosed(),
                anEntity.getCreatedAt(),
                anEntity.getUpdatedAt()
        );
    }

    private ComandaEntity(
            final String id,
            final String orderId,
            final boolean closed,
            final Instant createdAt,
            final Instant updatedAt) {
        this.id = id;
        this.orderId = orderId;
        this.closed = closed;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public ComandaEntity() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }
}
