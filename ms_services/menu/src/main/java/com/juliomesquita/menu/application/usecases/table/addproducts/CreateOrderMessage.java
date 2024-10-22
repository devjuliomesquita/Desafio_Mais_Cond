package com.juliomesquita.menu.application.usecases.table.addproducts;

import com.juliomesquita.menu.domain.table.TableRestaurant;

import java.util.Map;

public record CreateOrderMessage(String tableId, Map<String, Integer> products) {
    public static CreateOrderMessage from(TableRestaurant aTable) {
        return new CreateOrderMessage(aTable.getId(), aTable.getProducts());
    }
}
