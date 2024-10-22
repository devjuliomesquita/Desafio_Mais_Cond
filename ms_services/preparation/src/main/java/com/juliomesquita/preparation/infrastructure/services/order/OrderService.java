package com.juliomesquita.preparation.infrastructure.services.order;

import com.juliomesquita.preparation.infrastructure.services.order.models.OrderResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "order-service", url = "${application.config.order-url}")
public interface OrderService {
    @GetMapping(
            value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    Optional<OrderResponse> findOrderById(@PathVariable(name = "id") String id);
}
