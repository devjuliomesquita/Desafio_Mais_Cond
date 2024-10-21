package com.juliomesquita.menu.infrastruture.product.persistence;

import com.juliomesquita.menu.domain.product.Product;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Table(name = "tb_products")
@Entity
public class ProductEntity {
    @Id
    @Column(name = "product_id", nullable = false)
    private String id;

    @Column(name = "product_name", nullable = false)
    private String name;

    @Column(name = "product_description")
    private String description;

    @Column(name = "product_category", nullable = false)
    private String category;

    @Column(name = "product_price", nullable = false)
    private BigDecimal price;

    public static ProductEntity from(final Product aProduct) {
        return new ProductEntity(
                aProduct.getId(),
                aProduct.getName(),
                aProduct.getDescription(),
                aProduct.getCategory(),
                aProduct.getPrice()
        );
    }

    public static Product toAggregate(final ProductEntity entity) {
        return Product.with(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getCategory(),
                entity.getPrice()
        );
    }


    public ProductEntity() {
    }

    private ProductEntity(
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

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
