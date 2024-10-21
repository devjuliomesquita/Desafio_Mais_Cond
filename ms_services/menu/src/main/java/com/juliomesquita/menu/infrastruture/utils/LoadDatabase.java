package com.juliomesquita.menu.infrastruture.utils;

import com.juliomesquita.menu.infrastruture.product.persistence.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class LoadDatabase implements CommandLineRunner {
    private final ProductRepository productRepository;

    public LoadDatabase(final ProductRepository productRepository) {
        this.productRepository = Objects.requireNonNull(productRepository);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
