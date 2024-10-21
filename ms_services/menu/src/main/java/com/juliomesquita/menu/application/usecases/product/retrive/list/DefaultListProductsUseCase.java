package com.juliomesquita.menu.application.usecases.product.retrive.list;

import com.juliomesquita.menu.domain.commom.Pagination;
import com.juliomesquita.menu.domain.commom.SearchQuery;
import com.juliomesquita.menu.domain.product.ProductGateway;

import java.util.Objects;

public class DefaultListProductsUseCase extends ListProductsUseCase {

    private final ProductGateway productGateway;

    public DefaultListProductsUseCase(final ProductGateway productGateway) {
        this.productGateway = Objects.requireNonNull(productGateway);
    }

    @Override
    public Pagination<ListProductsOutput> execute(SearchQuery aCommand) {
        return this.productGateway.findAll(aCommand).map(ListProductsOutput::from);
    }
}
