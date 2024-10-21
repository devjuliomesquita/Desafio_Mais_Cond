package com.juliomesquita.menu.infrastruture.utils;

import org.springframework.data.jpa.domain.Specification;

public final class SpecificationUtils {
    private SpecificationUtils() {
    }

    public static  <T> Specification<T> like(final String property, final String terms) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(
                        criteriaBuilder.upper(root.get(property)),
                        like(terms.toUpperCase())
                );
    }

    private static String like(final String terms) {
        return "%" + terms + "%";
    }
}
