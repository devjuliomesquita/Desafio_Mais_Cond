package com.juliomesquita.menu.application.usecases.table.addproducts;

import java.util.Map;

public record AddProductsCommand(
        String tableId,
        Map<String, Integer> products
) {

}
