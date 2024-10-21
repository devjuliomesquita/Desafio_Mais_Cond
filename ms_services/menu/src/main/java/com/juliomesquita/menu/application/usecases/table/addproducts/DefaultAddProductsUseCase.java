package com.juliomesquita.menu.application.usecases.table.addproducts;

import com.juliomesquita.menu.domain.table.TableRestaurant;
import com.juliomesquita.menu.domain.table.TableRestaurantGateway;

import java.util.Objects;

public class DefaultAddProductsUseCase extends AddProductsUseCase {
    private final TableRestaurantGateway tableRestaurantGateway;

    public DefaultAddProductsUseCase(final TableRestaurantGateway tableRestaurantGateway) {
        this.tableRestaurantGateway = Objects.requireNonNull(tableRestaurantGateway);
    }

    @Override
    public String execute(AddProductsCommand aCommand) {
        final TableRestaurant aTableRestaurant = this.tableRestaurantGateway
                .findById(aCommand.tableId())
                .orElseThrow(() ->
                        new RuntimeException("'Table' with id :: %s not found".formatted(aCommand.tableId())));
        aTableRestaurant.addProducts(aCommand.products());
        return this.tableRestaurantGateway.save(aTableRestaurant);
    }
}
