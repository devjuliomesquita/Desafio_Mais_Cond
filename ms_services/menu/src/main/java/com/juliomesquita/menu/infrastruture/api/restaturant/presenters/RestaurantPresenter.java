package com.juliomesquita.menu.infrastruture.api.restaturant.presenters;

import com.juliomesquita.menu.application.usecases.table.addproducts.AddProductsCommand;
import com.juliomesquita.menu.application.usecases.table.retrive.getbyid.TableOutput;
import com.juliomesquita.menu.application.usecases.table.retrive.list.ListTablesOutput;
import com.juliomesquita.menu.infrastruture.api.restaturant.models.AddProductsRequest;
import com.juliomesquita.menu.infrastruture.api.restaturant.models.ListTableResponse;
import com.juliomesquita.menu.infrastruture.api.restaturant.models.ProductsListModel;
import com.juliomesquita.menu.infrastruture.api.restaturant.models.TableResponse;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public interface RestaurantPresenter {
    Function<ListTablesOutput, ListTableResponse> presenterList =
            output -> new ListTableResponse(output.id(), output.reserved());

    Function<TableOutput, TableResponse> presenterSimple =
            output -> {
                final List<ProductsListModel> listProducts = output.products().entrySet()
                        .stream()
                        .map(product -> new ProductsListModel(product.getKey(), product.getValue()))
                        .toList();
                return new TableResponse(output.id(), output.reserved(), listProducts);
            };
    Function<AddProductsRequest, AddProductsCommand> presenterAddProducts =
            request -> {
                final Map<String, Integer> mapProducts = request.products()
                        .stream()
                        .collect(Collectors.toMap(
                                ProductsListModel::id, ProductsListModel::quantity));
                return new AddProductsCommand(request.tableId(), mapProducts);
            };
}
