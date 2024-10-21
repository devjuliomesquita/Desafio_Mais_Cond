package com.juliomesquita.menu.domain.commom;

public record SearchQuery(
        int currentPage,
        int itemsPerPage,
        String terms,
        String sort,
        String direction
) {
}
