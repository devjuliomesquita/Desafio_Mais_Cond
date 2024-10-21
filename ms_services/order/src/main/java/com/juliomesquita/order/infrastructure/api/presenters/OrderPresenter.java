package com.juliomesquita.order.infrastructure.api.presenters;

import com.juliomesquita.order.application.usecase.order.retrive.getbyid.OrderOutput;
import com.juliomesquita.order.application.usecase.order.retrive.list.ListOrderOutput;
import com.juliomesquita.order.infrastructure.api.models.ListOrdersResponse;
import com.juliomesquita.order.infrastructure.api.models.OrderResponse;
import com.juliomesquita.order.infrastructure.api.models.ProductsModel;

import java.util.List;
import java.util.function.Function;

public interface OrderPresenter {
    Function<OrderOutput, OrderResponse> presenterSimple =
            output -> {
                final List<ProductsModel> products = output.products().entrySet()
                        .stream()
                        .map(product -> new ProductsModel(product.getKey(), product.getValue()))
                        .toList();
                return new OrderResponse(
                        output.id(),
                        output.tableId(),
                        products,
                        output.createdAt(),
                        output.updatedAt(),
                        output.status().toString()
                );
            };

    Function<ListOrderOutput, ListOrdersResponse> presenterList =
            output -> new ListOrdersResponse(
                    output.id(),
                    output.tableId(),
                    output.createdAt(),
                    output.status().toString());
    ;
}
