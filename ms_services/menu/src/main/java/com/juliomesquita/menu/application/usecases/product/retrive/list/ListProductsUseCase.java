package com.juliomesquita.menu.application.usecases.product.retrive.list;

import com.juliomesquita.menu.domain.commom.Pagination;
import com.juliomesquita.menu.domain.commom.SearchQuery;

public abstract class ListProductsUseCase {
    public abstract Pagination<ListProductsOutput> execute(SearchQuery aCommand);
}
