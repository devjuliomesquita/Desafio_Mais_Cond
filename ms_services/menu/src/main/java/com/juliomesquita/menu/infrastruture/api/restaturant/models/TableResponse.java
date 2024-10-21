package com.juliomesquita.menu.infrastruture.api.restaturant.models;

import java.util.List;

public record TableResponse(
        String id,
        boolean reserved,
        List<ProductsListModel> products
) {
}
