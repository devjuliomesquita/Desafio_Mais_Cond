package com.juliomesquita.menu.application.usecases.table.retrive.getbyid;

import com.juliomesquita.menu.domain.table.TableRestaurantGateway;

import java.util.Objects;

public class DefaultGetTableByIdUseCase extends GetTableByIdUseCase {
    private final TableRestaurantGateway tableRestaurantGateway;

    public DefaultGetTableByIdUseCase(final TableRestaurantGateway tableRestaurantGateway) {
        this.tableRestaurantGateway = Objects.requireNonNull(tableRestaurantGateway);
    }

    @Override
    public TableOutput execute(String aCommand) {
        return this.tableRestaurantGateway.findById(aCommand)
                .map(TableOutput::from)
                .orElseThrow(() -> new RuntimeException("'Table' with id :: %s not found".formatted(aCommand)));
    }
}
