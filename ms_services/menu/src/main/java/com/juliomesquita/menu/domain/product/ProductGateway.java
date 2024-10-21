package com.juliomesquita.menu.domain.product;

import com.juliomesquita.menu.domain.commom.Pagination;
import com.juliomesquita.menu.domain.commom.SearchQuery;

public interface ProductGateway {
    Pagination<Product> findAll(SearchQuery aQuery);
}
