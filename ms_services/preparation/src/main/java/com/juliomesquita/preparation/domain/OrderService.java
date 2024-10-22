package com.juliomesquita.preparation.domain;

import com.juliomesquita.preparation.domain.dto.OrderDTO;

public interface OrderService {
    boolean existOrderById(String anOrderId);
    OrderDTO findOrderById(String anOrderId);
}
