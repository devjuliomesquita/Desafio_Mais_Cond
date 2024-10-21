package com.juliomesquita.menu.application.usecases.product.retrive.list;

import com.juliomesquita.menu.domain.product.Product;

import java.math.BigDecimal;

public record ListProductsOutput(
        String id,
        String name,
        String description,
        String category,
        BigDecimal price
) {
    public static ListProductsOutput from(final Product aProduct) {
        return new ListProductsOutput(
                aProduct.getId(),
                aProduct.getName(),
                aProduct.getDescription(),
                aProduct.getCategory(),
                aProduct.getPrice()
        );
    }
}
