package com.juliomesquita.menu.infrastruture.table.persistence;

import com.juliomesquita.menu.domain.table.TableRestaurant;
import jakarta.persistence.*;

import java.util.Map;

@Table(name = "tb_tables")
@Entity
public class TableEntity {
    @Id
    @Column(name = "table_id", nullable = false)
    private String id;

    @Column(name = "table_reserved", nullable = false)
    private boolean reserved;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "table_products", joinColumns = @JoinColumn(name = "table_id"))
    @MapKeyColumn(name = "product_id")
    @Column(name = "product_quantity")
    private Map<String, Integer> products;

    public static TableEntity from(final TableRestaurant aTableRestaurant) {
        return new TableEntity(
                aTableRestaurant.getId(),
                aTableRestaurant.isReserved(),
                aTableRestaurant.getProducts());
    }

    public static TableRestaurant toAggregate(final TableEntity entity) {
        return TableRestaurant.with(
                entity.getId(),
                entity.isReserved(),
                entity.getProducts()
        );
    }

    private TableEntity(
            final String id,
            final boolean reserved,
            final Map<String, Integer> products
    ) {
        this.id = id;
        this.reserved = reserved;
        this.products = products;
    }

    public TableEntity() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    public Map<String, Integer> getProducts() {
        return products;
    }

    public void setProducts(Map<String, Integer> products) {
        this.products = products;
    }
}
