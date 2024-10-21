package com.juliomesquita.menu.domain.product;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Product-Domain-Test")
class ProductTest {

    @Test
    void givenAValidParams_whenCallsNewProduct_thenReturnAProduct() {
        //given
        final String expectedName = "Pizza Marguerita";
        final String expectedDescription = "Feita com molho de tomate... ";
        final String expectedCategory = "Massas";
        final BigDecimal expectedPrice = BigDecimal.valueOf(49.9);

        //when
        final Product aProduct = Product.newProduct(expectedName, expectedDescription, expectedCategory, expectedPrice);

        //then
        assertNotNull(aProduct);
        assertNotNull(aProduct.getId());
        assertEquals(expectedName, aProduct.getName());
        assertEquals(expectedDescription, aProduct.getDescription());
        assertEquals(expectedCategory, aProduct.getCategory());
        assertEquals(expectedPrice, aProduct.getPrice());
    }

    @Test
    void givenAnInvalidParamNameIsNull_whenCallsNewProduct_thenReturnAnException() {
        //given
        final String expectedName = null;
        final String expectedDescription = "Feita com molho de tomate... ";
        final String expectedCategory = "Massas";
        final BigDecimal expectedPrice = BigDecimal.valueOf(49.9);
        final String expectedMessageError = "'name' should be not null";

        //when
        final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> Product.newProduct(expectedName, expectedDescription, expectedCategory, expectedPrice));

        //then
        assertEquals(expectedMessageError, exception.getMessage());
    }

    @Test
    void givenAnInvalidParamNameIsBlank_whenCallsNewProduct_thenReturnAnException() {
        //given
        final String expectedName = "";
        final String expectedDescription = "Feita com molho de tomate... ";
        final String expectedCategory = "Massas";
        final BigDecimal expectedPrice = BigDecimal.valueOf(49.9);
        final String expectedMessageError = "'name' should be not blank";

        //when
        final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> Product.newProduct(expectedName, expectedDescription, expectedCategory, expectedPrice));

        //then
        assertEquals(expectedMessageError, exception.getMessage());
    }

    @Test
    void givenAnInvalidParamNamelessThan3_whenCallsNewProduct_thenReturnAnException() {
        //given
        final String expectedName = "as   ";
        final String expectedDescription = "Feita com molho de tomate... ";
        final String expectedCategory = "Massas";
        final BigDecimal expectedPrice = BigDecimal.valueOf(49.9);
        final String expectedMessageError = "'name' must be between 3 and 255 characters";

        //when
        final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> Product.newProduct(expectedName, expectedDescription, expectedCategory, expectedPrice));

        //then
        assertEquals(expectedMessageError, exception.getMessage());
    }

    @Test
    void givenAnInvalidParamNameMoreThan255_whenCallsNewProduct_thenReturnAnException() {
        //given
        final String expectedName = """
                É claro que o consenso sobre a necessidade de qualificação garante a contribuição de um grupo importante 
                na determinação do sistema de participação geral. Por outro lado, a complexidade dos estudos efetuados 
                afeta positivamente a correta previsão do processo de comunicação como um todo.
                """;
        final String expectedDescription = "Feita com molho de tomate... ";
        final String expectedCategory = "Massas";
        final BigDecimal expectedPrice = BigDecimal.valueOf(49.9);
        final String expectedMessageError = "'name' must be between 3 and 255 characters";

        //when
        final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> Product.newProduct(expectedName, expectedDescription, expectedCategory, expectedPrice));

        //then
        assertEquals(expectedMessageError, exception.getMessage());
    }

    @Test
    void givenAnInvalidParamPriceValueLessThanZero_whenCallsNewProduct_thenReturnAnException() {
        //given
        final String expectedName = "Pizza Marguerita";
        final String expectedDescription = "Feita com molho de tomate... ";
        final String expectedCategory = "Massas";
        final BigDecimal expectedPrice = BigDecimal.valueOf(-49.9);
        final String expectedMessageError = "'price' should be not less then zero";

        //when
        final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> Product.newProduct(expectedName, expectedDescription, expectedCategory, expectedPrice));

        //then
        assertEquals(expectedMessageError, exception.getMessage());
    }

}