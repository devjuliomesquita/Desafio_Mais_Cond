package com.juliomesquita.menu.domain.table;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TableRestaurant {
    private String id;
    private boolean reserved;
    private Map<String, Integer> products;

    public static TableRestaurant newTable(final boolean aReserved) {
        final String anId = UUID.randomUUID().toString();
        final Map<String, Integer> aMap = new HashMap<>();
        return new TableRestaurant(anId, aReserved, aMap);
    }

    public static TableRestaurant with(
            final String anId,
            final boolean aReserved,
            final Map<String, Integer> aProducts
    ) {
        return new TableRestaurant(anId, aReserved, aProducts);
    }

    public void addProducts(final Map<String, Integer> newProducts) {
        newProducts.entrySet()
                .stream()
                .filter(product -> product.getValue() > 0)
                .filter(product -> !product.getKey().isBlank())
                .forEach(product ->
                        this.products.put(product.getKey(), product.getValue()));
        if (!this.products.isEmpty()) {
            this.reserved = true;
        }
    }

    private TableRestaurant(
            final String id,
            final boolean reserved,
            final Map<String, Integer> products) {
        this.id = id;
        this.reserved = reserved;
        this.products = products;
    }

    public String getId() {
        return id;
    }

    public boolean isReserved() {
        return reserved;
    }

    public Map<String, Integer> getProducts() {
        return products;
    }
}
