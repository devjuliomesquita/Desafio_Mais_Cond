package com.juliomesquita.order.domain;

import com.juliomesquita.order.domain.entities.Order;

import java.util.List;
import java.util.Optional;

public interface OrderGateway {
    String save(Order anOrder);

    Optional<Order> getById(String anId);

    List<Order> getAll();
}
