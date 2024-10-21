package com.juliomesquita.order.infrastructure.order.persistence;

import com.juliomesquita.order.domain.entities.Order;
import com.juliomesquita.order.domain.enums.OrderStatus;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.Map;

@Table(name = "tb_orders")
@Entity
public class OrderEntity {
    @Id
    @Column(name = "order_id", nullable = false)
    private String id;

    @Column(name = "order_table_id", nullable = false)
    private String tableId;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "order_products", joinColumns = @JoinColumn(name = "order_id"))
    @MapKeyColumn(name = "product_id")
    @Column(name = "product_quantity")
    private Map<String, Integer> products;

    @Column(name = "order_created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "order_updated_at", nullable = false)
    private Instant updatedAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status", nullable = false)
    private OrderStatus status;

    public static OrderEntity from(final Order anOrder) {
        return new OrderEntity(
                anOrder.getId(),
                anOrder.getTableId(),
                anOrder.getProducts(),
                anOrder.getCreatedAt(),
                anOrder.getUpdatedAt(),
                anOrder.getStatus()
        );
    }

    public static Order toAggregate(final OrderEntity anEntity) {
        return Order.with(
                anEntity.getId(),
                anEntity.getTableId(),
                anEntity.getProducts(),
                anEntity.getCreatedAt(),
                anEntity.getUpdatedAt(),
                anEntity.getStatus()
        );
    }

    private OrderEntity(
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

    public OrderEntity() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    public Map<String, Integer> getProducts() {
        return products;
    }

    public void setProducts(Map<String, Integer> products) {
        this.products = products;
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

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
