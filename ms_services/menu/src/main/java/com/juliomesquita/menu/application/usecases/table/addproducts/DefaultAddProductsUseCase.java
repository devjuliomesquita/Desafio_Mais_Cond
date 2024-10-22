package com.juliomesquita.menu.application.usecases.table.addproducts;

import com.juliomesquita.menu.domain.commom.SentEventService;
import com.juliomesquita.menu.domain.table.TableRestaurant;
import com.juliomesquita.menu.domain.table.TableRestaurantGateway;

import java.util.Map;
import java.util.Objects;

public class DefaultAddProductsUseCase extends AddProductsUseCase {
    private final TableRestaurantGateway tableRestaurantGateway;
    private final SentEventService<CreateOrderMessage> sentEventService;

    public DefaultAddProductsUseCase(
            final TableRestaurantGateway tableRestaurantGateway,
            final SentEventService<CreateOrderMessage> sentEventService
            ) {
        this.tableRestaurantGateway = Objects.requireNonNull(tableRestaurantGateway);
        this.sentEventService = Objects.requireNonNull(sentEventService);
    }

    @Override
    public String execute(AddProductsCommand aCommand) {
        final TableRestaurant aTableRestaurant = this.tableRestaurantGateway
                .findById(aCommand.tableId())
                .orElseThrow(() ->
                        new RuntimeException("'Table' with id :: %s not found".formatted(aCommand.tableId())));
        aTableRestaurant.addProducts(aCommand.products());
        this.sentEventService.sentEvent(CreateOrderMessage.from(aTableRestaurant));
        return this.tableRestaurantGateway.save(aTableRestaurant);
    }

    private Map<String, Map<String, Integer>> toMap(final TableRestaurant aTableRestaurant){
        return Map.of(aTableRestaurant.getId(), aTableRestaurant.getProducts());
    }
}
