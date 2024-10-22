package com.juliomesquita.menu.infrastruture.configurations.beans;

import com.juliomesquita.menu.application.usecases.table.addproducts.AddProductsUseCase;
import com.juliomesquita.menu.application.usecases.table.addproducts.CreateOrderMessage;
import com.juliomesquita.menu.application.usecases.table.addproducts.DefaultAddProductsUseCase;
import com.juliomesquita.menu.application.usecases.table.retrive.getbyid.DefaultGetTableByIdUseCase;
import com.juliomesquita.menu.application.usecases.table.retrive.getbyid.GetTableByIdUseCase;
import com.juliomesquita.menu.application.usecases.table.retrive.list.DefaultListTablesUseCase;
import com.juliomesquita.menu.application.usecases.table.retrive.list.ListTablesUseCase;
import com.juliomesquita.menu.domain.commom.SentEventService;
import com.juliomesquita.menu.domain.table.TableRestaurantGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.Objects;

@Configuration
public class TableBeansConfig {
    private final TableRestaurantGateway tableRestaurantGateway;
    private final SentEventService<CreateOrderMessage> sentEventService;

    public TableBeansConfig(
            final TableRestaurantGateway tableRestaurantGateway,
            final SentEventService<CreateOrderMessage> sentEventService
    ) {
        this.tableRestaurantGateway = Objects.requireNonNull(tableRestaurantGateway);
        this.sentEventService = Objects.requireNonNull(sentEventService);
    }

    @Bean
    public AddProductsUseCase addProductsUseCase() {
        return new DefaultAddProductsUseCase(tableRestaurantGateway, sentEventService);
    }

    @Bean
    public GetTableByIdUseCase getTableByIdUseCase() {
        return new DefaultGetTableByIdUseCase(tableRestaurantGateway);
    }

    @Bean
    public ListTablesUseCase listTablesUseCase() {
        return new DefaultListTablesUseCase(tableRestaurantGateway);
    }
}
