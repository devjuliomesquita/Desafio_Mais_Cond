package com.juliomesquita.menu.infrastruture.utils;

import com.juliomesquita.menu.domain.product.Product;
import com.juliomesquita.menu.domain.table.TableRestaurant;
import com.juliomesquita.menu.domain.table.TableRestaurantGateway;
import com.juliomesquita.menu.infrastruture.product.persistence.ProductEntity;
import com.juliomesquita.menu.infrastruture.product.persistence.ProductRepository;
import com.juliomesquita.menu.infrastruture.table.persistence.TableEntity;
import com.juliomesquita.menu.infrastruture.table.persistence.TableRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Component
public class LoadDatabase implements CommandLineRunner {
    private final ProductRepository productRepository;
    private final TableRepository tableRepository;

    public LoadDatabase(
            final ProductRepository productRepository,
            final TableRepository tableRepository
    ) {
        this.productRepository = Objects.requireNonNull(productRepository);
        this.tableRepository = Objects.requireNonNull(tableRepository);
    }

    @Transactional
    @Override
    public void run(String... args) throws Exception {

        final List<ProductEntity> listProducts = this.createListProducts();
        final List<TableEntity> listTables = this.createListTables();
        this.productRepository.saveAll(listProducts);
        this.tableRepository.saveAll(listTables);
    }

    private List<TableEntity> createListTables() {
        return Stream.of(
                        TableRestaurant.newTable(false),
                        TableRestaurant.newTable(false),
                        TableRestaurant.newTable(false),
                        TableRestaurant.newTable(false),
                        TableRestaurant.newTable(false)
                )
                .map(TableEntity::from)
                .toList();
    }

    private List<ProductEntity> createListProducts() {
        return Stream.of(
                        Product.newProduct(
                                "La Principessa Margherita",
                                """
                                        Molho de tomate pelato roma, mozzarella flor de leite, queijo parmesão ralado, majericão 
                                        e azeite EVO.
                                        """,
                                "Massas",
                                BigDecimal.valueOf(53.7)),
                        Product.newProduct(
                                "Marinara",
                                """
                                        Molho de tomate pelato roma, alho e azeite EVO.
                                        """,
                                "Massas",
                                BigDecimal.valueOf(35.7)),
                        Product.newProduct(
                                "Gênova",
                                """
                                        Molho de tomate pelato roma, mozzarella flor de leite, tomate cereja confit e molho 
                                        pesto de manjericão.
                                        """,
                                "Massas",
                                BigDecimal.valueOf(59.7)),
                        Product.newProduct(
                                "Bella Parma",
                                """
                                        Molho de tomate pelato roma, mozzarella flor de leite, parma, parmesão, rúcula e azeite EVO.
                                        """,
                                "Massas",
                                BigDecimal.valueOf(66.7))

                ).map(ProductEntity::from)
                .toList();
    }
}
