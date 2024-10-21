package com.juliomesquita.menu.application.usecases.product.retrive.list;

import com.juliomesquita.menu.domain.commom.Pagination;
import com.juliomesquita.menu.domain.commom.SearchQuery;
import com.juliomesquita.menu.domain.product.Product;
import com.juliomesquita.menu.domain.product.ProductGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class DefaultListProductsUseCaseTest {
    @InjectMocks
    private DefaultListProductsUseCase useCase;

    @Mock
    private ProductGateway productGateway;

    @BeforeEach
    void cleanUp() {
        Mockito.reset(productGateway);
    }

    @Test
    void givenAValidQuery_whenCallsListProducts_thenReturnListProducts() {
        //given
        final List<Product> products =
                List.of(
                        Product.newProduct("Pizza Marguerita", "Molho de tomate...", "Massas", BigDecimal.valueOf(49.9)),
                        Product.newProduct("Pizza Portuguesa", "Molho de tomate...", "Massas", BigDecimal.valueOf(59.9)),
                        Product.newProduct("Pizza Calabresa", "Molho de tomate...", "Massas", BigDecimal.valueOf(39.9)),
                        Product.newProduct("Pizza Frango com Catupiry", "Molho de tomate...", "Massas", BigDecimal.valueOf(69.9)));
        final int expectedCurrentPage = 0;
        final int expectedItemsPerPage = 4;
        final String expectedTerms = "";
        final String expectedSort = "name";
        final String expectedDirection = "ASC";
        final long expectedTotalItems = products.size();
        final int expectedTotalPages = (int) expectedTotalItems / expectedItemsPerPage;
        final SearchQuery aQuery =
                new SearchQuery(expectedCurrentPage, expectedItemsPerPage, expectedTerms, expectedSort, expectedDirection);
        final Pagination<Product> expectedPagination =
                new Pagination<>(products, expectedCurrentPage, expectedItemsPerPage, expectedTotalItems, expectedTotalPages);

        //when
        when(this.productGateway.findAll(eq(aQuery))).thenReturn(expectedPagination);
        final Pagination<ListProductsOutput> execute = this.useCase.execute(aQuery);

        //then
        assertAll("Verify attributes of product", () -> {
            assertEquals(expectedCurrentPage, execute.currentPage());
            assertEquals(expectedItemsPerPage, execute.itemsPerPage());
            assertEquals(expectedTotalItems, execute.totalItems());
            assertEquals(expectedTotalPages, execute.totalPages());
            assertEquals(products.get(0).getId(), execute.items().get(0).id());
        });
        verify(this.productGateway, times(1)).findAll(eq(aQuery));
    }

    @Test
    void givenAValidQuery_whenCallsListProducts_thenReturnListEmptyProducts() {
        //given
        final List<Product> products = List.of();
        final int expectedCurrentPage = 0;
        final int expectedItemsPerPage = 4;
        final String expectedTerms = "";
        final String expectedSort = "name";
        final String expectedDirection = "ASC";
        final long expectedTotalItems = 0;
        final int expectedTotalPages = 0;
        final SearchQuery aQuery =
                new SearchQuery(expectedCurrentPage, expectedItemsPerPage, expectedTerms, expectedSort, expectedDirection);
        final Pagination<Product> expectedPagination =
                new Pagination<>(products, expectedCurrentPage, expectedItemsPerPage, expectedTotalItems, expectedTotalPages);

        //when
        when(this.productGateway.findAll(eq(aQuery))).thenReturn(expectedPagination);
        final Pagination<ListProductsOutput> execute = this.useCase.execute(aQuery);

        //then
        assertAll("Verify attributes of product", () -> {
            assertEquals(expectedCurrentPage, execute.currentPage());
            assertEquals(expectedItemsPerPage, execute.itemsPerPage());
            assertEquals(expectedTotalItems, execute.totalItems());
            assertEquals(expectedTotalPages, execute.totalPages());
        });
        verify(this.productGateway, times(1)).findAll(eq(aQuery));
    }

    @Test
    void givenAValidQuery_whenCallsListProducts_thenReturnAnyException() {
        //given
        final int expectedCurrentPage = 0;
        final int expectedItemsPerPage = 4;
        final String expectedTerms = "";
        final String expectedSort = "name";
        final String expectedDirection = "ASC";
        final SearchQuery aQuery =
                new SearchQuery(expectedCurrentPage, expectedItemsPerPage, expectedTerms, expectedSort, expectedDirection);
        final String expectedMessageError = "Error Gateway";

        //when
        when(this.productGateway.findAll(eq(aQuery))).thenThrow(new IllegalStateException(expectedMessageError));

        //then
        final IllegalStateException exception = assertThrows(IllegalStateException.class,
                () -> this.useCase.execute(aQuery));
        assertEquals(expectedMessageError, exception.getMessage());
        verify(this.productGateway, times(1)).findAll(eq(aQuery));
    }

}