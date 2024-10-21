package com.juliomesquita.menu.infrastruture.api.product.presenter;

import com.juliomesquita.menu.application.usecases.product.retrive.list.ListProductsOutput;
import com.juliomesquita.menu.infrastruture.api.product.models.ListProductsResponse;

import java.util.function.Function;

public interface ProductApiPresenter {
    Function<ListProductsOutput, ListProductsResponse> presentList =
            output -> new ListProductsResponse(
                    output.id(),
                    output.name(),
                    output.description(),
                    output.category(),
                    output.price()
            );
}
