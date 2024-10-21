package com.juliomesquita.order.infrastructure.order;

import com.juliomesquita.order.domain.OrderGateway;
import com.juliomesquita.order.domain.entities.Order;
import com.juliomesquita.order.infrastructure.order.persistence.OrderEntity;
import com.juliomesquita.order.infrastructure.order.persistence.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class OrderH2Gateway implements OrderGateway {
    private final OrderRepository orderRepository;

    public OrderH2Gateway(final OrderRepository orderRepository) {
        this.orderRepository = Objects.requireNonNull(orderRepository);
    }

    @Override
    public String save(Order anOrder) {
        final OrderEntity anEntity = OrderEntity.from(anOrder);
        return this.orderRepository.save(anEntity).getId();
    }

    @Override
    public Optional<Order> getById(String anId) {
        return this.orderRepository.findById(anId)
                .map(OrderEntity::toAggregate);
    }

    @Override
    public List<Order> getAll() {
        return this.orderRepository
                .findAll()
                .stream()
                .map(OrderEntity::toAggregate)
                .toList();
    }
}
