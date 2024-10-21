package com.juliomesquita.menu.domain.product;

import java.math.BigDecimal;
import java.util.UUID;

public class Product {
    private String id;
    private String name;
    private String description;
    private String category;
    private BigDecimal price;

    public static Product newProduct(
            final String aName,
            final String aDescription,
            final String aCategory,
            final BigDecimal aPrice
    ) {
        final String anId = UUID.randomUUID().toString();
        validate(aName, aPrice);
        return new Product(anId, aName, aDescription, aCategory, aPrice);
    }

    public static Product with(
            final String anId,
            final String aName,
            final String aDescription,
            final String aCategory,
            final BigDecimal aPrice
    ) {
        return new Product(anId, aName, aDescription, aCategory, aPrice);
    }

    private static void validate(final String aName, final BigDecimal aPrice) {
        if (aName == null) {
            throw new IllegalArgumentException("'name' should be not null");
        }

        if (aName.isBlank()) {
            throw new IllegalArgumentException("'name' should be not blank");
        }

        if (aName.trim().length() < 3 || aName.trim().length() > 255) {
            throw new IllegalArgumentException("'name' must be between 3 and 255 characters");
        }

        if (aPrice.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("'price' should be not less then zero");
        }
    }

    private Product(
            final String id,
            final String name,
            final String description,
            final String category,
            final BigDecimal price
    ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
