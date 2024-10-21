package com.juliomesquita.menu.infrastruture.configurations.beans;

import com.juliomesquita.menu.application.usecases.product.retrive.list.DefaultListProductsUseCase;
import com.juliomesquita.menu.application.usecases.product.retrive.list.ListProductsUseCase;
import com.juliomesquita.menu.domain.product.ProductGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

@Configuration
public class ProductBeansConfig {
    private final ProductGateway productGateway;

    public ProductBeansConfig(final ProductGateway productGateway) {
        this.productGateway = Objects.requireNonNull(productGateway);
    }

    @Bean
    public ListProductsUseCase listProductsUseCase() {
        return new DefaultListProductsUseCase(productGateway);
    }
}
