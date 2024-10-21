package com.juliomesquita.menu.infrastruture.api.product.controllers;

import com.juliomesquita.menu.application.usecases.product.retrive.list.ListProductsUseCase;
import com.juliomesquita.menu.domain.commom.Pagination;
import com.juliomesquita.menu.domain.commom.SearchQuery;
import com.juliomesquita.menu.infrastruture.api.product.ProductAPI;
import com.juliomesquita.menu.infrastruture.api.product.models.ListProductsResponse;
import com.juliomesquita.menu.infrastruture.api.product.presenter.ProductApiPresenter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class ProductController implements ProductAPI {
    private final ListProductsUseCase listProductsUseCase;

    public ProductController(final ListProductsUseCase listProductsUseCase) {
        this.listProductsUseCase = Objects.requireNonNull(listProductsUseCase);
    }


    @Override
    public ResponseEntity<Pagination<ListProductsResponse>> ListProducts(
            final String search,
            final int currentPage,
            final int itemsPerPage,
            final String sort,
            final String direction
    ) {
        final Pagination<ListProductsResponse> response = this.listProductsUseCase.execute(
                        new SearchQuery(currentPage, itemsPerPage, search, sort, direction))
                .map(ProductApiPresenter.presentList);
        return ResponseEntity.ok(response);
    }
}
