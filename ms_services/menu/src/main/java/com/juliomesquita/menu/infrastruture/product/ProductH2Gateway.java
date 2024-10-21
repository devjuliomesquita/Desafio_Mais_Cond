package com.juliomesquita.menu.infrastruture.product;

import com.juliomesquita.menu.domain.commom.Pagination;
import com.juliomesquita.menu.domain.commom.SearchQuery;
import com.juliomesquita.menu.domain.product.Product;
import com.juliomesquita.menu.domain.product.ProductGateway;
import com.juliomesquita.menu.infrastruture.product.persistence.ProductEntity;
import com.juliomesquita.menu.infrastruture.product.persistence.ProductRepository;
import com.juliomesquita.menu.infrastruture.utils.SpecificationUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

import static com.juliomesquita.menu.infrastruture.utils.SpecificationUtils.*;

@Service
public class ProductH2Gateway implements ProductGateway {
    private final ProductRepository productRepository;

    public ProductH2Gateway(final ProductRepository productRepository) {
        this.productRepository = Objects.requireNonNull(productRepository);
    }

    @Override
    public Pagination<Product> findAll(SearchQuery aQuery) {
        final PageRequest pageRequest = PageRequest.of(
                aQuery.currentPage(),
                aQuery.itemsPerPage(),
                Sort.by(Sort.Direction.fromString(aQuery.direction()), aQuery.sort()));

        final Specification<ProductEntity> specification = Optional.ofNullable(aQuery.terms())
                .filter(str -> !str.isBlank())
                .map(str -> {
                    final Specification<ProductEntity> nameLike = like("name", str);
                    final Specification<ProductEntity> descriptionLike = like("description", str);
                    final Specification<ProductEntity> categoryLike = like("category", str);
                    return nameLike.or(descriptionLike).or(categoryLike);
                })
                .orElse(null);

        final Page<ProductEntity> productList = this.productRepository.findAll(specification, pageRequest);
        return new Pagination<>(
                productList.map(ProductEntity::toAggregate).toList(),
                productList.getNumber(),
                productList.getSize(),
                productList.getTotalElements(),
                productList.getTotalPages()
        );
    }
}
