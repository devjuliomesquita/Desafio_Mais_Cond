package com.juliomesquita.menu.domain.table;

import java.util.List;
import java.util.Optional;

public interface TableRestaurantGateway {
    String save(TableRestaurant aTableRestaurant);

    Optional<TableRestaurant> findById(String anId);

    List<TableRestaurant> findAll();
}
