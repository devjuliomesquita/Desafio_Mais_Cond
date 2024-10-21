package com.juliomesquita.menu.application.usecases.table.retrive.list;

import com.juliomesquita.menu.domain.table.TableRestaurantGateway;

import java.util.List;
import java.util.Objects;

public class DefaultListTablesUseCase extends ListTablesUseCase {
    private final TableRestaurantGateway tableRestaurantGateway;

    public DefaultListTablesUseCase(final TableRestaurantGateway tableRestaurantGateway) {
        this.tableRestaurantGateway = Objects.requireNonNull(tableRestaurantGateway);
    }

    @Override
    public List<ListTablesOutput> execute() {
        return this.tableRestaurantGateway.findAll().stream()
                .map(ListTablesOutput::from)
                .toList();
    }
}
