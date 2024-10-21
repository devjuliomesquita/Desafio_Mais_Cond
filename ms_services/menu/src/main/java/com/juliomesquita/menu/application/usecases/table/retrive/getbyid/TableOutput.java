package com.juliomesquita.menu.application.usecases.table.retrive.getbyid;

import com.juliomesquita.menu.domain.table.TableRestaurant;

import java.util.Map;

public record TableOutput(
        String id,
        boolean reserved,
        Map<String, Integer> products
) {
    public static TableOutput from(final TableRestaurant aTableRestaurant) {
        return new TableOutput(
                aTableRestaurant.getId(),
                aTableRestaurant.isReserved(),
                aTableRestaurant.getProducts()
        );
    }
}
