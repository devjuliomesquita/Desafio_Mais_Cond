package com.juliomesquita.menu.domain.commom;

import java.util.List;
import java.util.function.Function;

public record Pagination<T>(
        List<T> items,
        int currentPage,
        int itemsPerPage,
        long totalItems,
        int totalPages
) {
    public <R> Pagination<R> map(final Function<T, R> mapper) {
        final List<R> aNewList = this.items.stream()
                .map(mapper)
                .toList();
        return new Pagination<>(aNewList, currentPage(), itemsPerPage(), totalItems(), totalPages());
    }
}
