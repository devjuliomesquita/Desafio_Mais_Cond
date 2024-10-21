package com.juliomesquita.menu.infrastruture.api.restaturant.models;

import java.util.List;



public record AddProductsRequest(
        String tableId,
        List<ProductsListModel> products
) {

}
