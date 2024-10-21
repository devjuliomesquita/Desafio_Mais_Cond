package com.juliomesquita.menu.infrastruture.api.restaturant.controllers;

import com.juliomesquita.menu.application.usecases.table.addproducts.AddProductsCommand;
import com.juliomesquita.menu.application.usecases.table.addproducts.AddProductsUseCase;
import com.juliomesquita.menu.application.usecases.table.retrive.getbyid.GetTableByIdUseCase;
import com.juliomesquita.menu.application.usecases.table.retrive.getbyid.TableOutput;
import com.juliomesquita.menu.application.usecases.table.retrive.list.ListTablesUseCase;
import com.juliomesquita.menu.domain.commom.Pagination;
import com.juliomesquita.menu.infrastruture.api.restaturant.RestaurantAPI;
import com.juliomesquita.menu.infrastruture.api.restaturant.models.AddProductsRequest;
import com.juliomesquita.menu.infrastruture.api.restaturant.models.ListTableResponse;
import com.juliomesquita.menu.infrastruture.api.restaturant.models.TableResponse;
import com.juliomesquita.menu.infrastruture.api.restaturant.presenters.RestaurantPresenter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
public class RestaurantController implements RestaurantAPI {
    private final ListTablesUseCase listTablesUseCase;
    private final GetTableByIdUseCase getTableByIdUseCase;
    private final AddProductsUseCase addProductsUseCase;

    public RestaurantController(
            final ListTablesUseCase listTablesUseCase,
            final GetTableByIdUseCase getTableByIdUseCase,
            final AddProductsUseCase addProductsUseCase) {
        this.listTablesUseCase = Objects.requireNonNull(listTablesUseCase);
        this.getTableByIdUseCase = Objects.requireNonNull(getTableByIdUseCase);
        this.addProductsUseCase = Objects.requireNonNull(addProductsUseCase);
    }

    @Override
    public ResponseEntity<List<ListTableResponse>> ListTablesRestaurant() {
        final List<ListTableResponse> tableResponseList = this.listTablesUseCase
                .execute()
                .stream()
                .map(RestaurantPresenter.presenterList)
                .toList();
        return ResponseEntity.ok(tableResponseList);
    }

    @Override
    public ResponseEntity<TableResponse> getTableById(final String id) {
        final TableResponse response = RestaurantPresenter.presenterSimple
                .compose(this.getTableByIdUseCase::execute)
                .apply(id);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<?> addProducts(final AddProductsRequest input) {
        final AddProductsCommand aCommand = RestaurantPresenter.presenterAddProducts
                .apply(input);
        return ResponseEntity.ok(this.addProductsUseCase.execute(aCommand));
    }
}
