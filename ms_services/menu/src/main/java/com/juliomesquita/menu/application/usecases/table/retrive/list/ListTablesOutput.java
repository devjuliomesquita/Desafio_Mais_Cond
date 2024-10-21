package com.juliomesquita.menu.application.usecases.table.retrive.list;

import com.juliomesquita.menu.domain.table.TableRestaurant;

import java.util.Map;

public record ListTablesOutput(
        String id,
        boolean reserved,
        Map<String, Integer> products
) {
    public static ListTablesOutput from(final TableRestaurant aTableRestaurant) {
        return new ListTablesOutput(
                aTableRestaurant.getId(),
                aTableRestaurant.isReserved(),
                aTableRestaurant.getProducts()
        );
    }
}
