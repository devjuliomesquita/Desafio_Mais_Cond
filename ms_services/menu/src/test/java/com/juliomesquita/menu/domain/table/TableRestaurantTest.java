package com.juliomesquita.menu.domain.table;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Table-Domain-Test")
class TableRestaurantTest {

    @Test
    void givenAValidParams_whenCallsNewTable_thenReturnTable() {
        //given
        final boolean expectedReserved = false;

        //when
        final TableRestaurant aTableRestaurant = TableRestaurant.newTable(expectedReserved);

        //then
        assertAll("Verify attributes table", () -> {
            assertNotNull(aTableRestaurant);
            assertNotNull(aTableRestaurant.getId());
            assertEquals(expectedReserved, aTableRestaurant.isReserved());
            assertNotNull(aTableRestaurant.getProducts());
            assertEquals(0, aTableRestaurant.getProducts().size());
        });
    }

    @Test
    void givenAValidProducts_whenCallsAddProducts_thenReturnTableUpdated() {
        //given
        final boolean expectedReserved = true;

        final String expectedProductId = "270a78a4-1467-472f-a4c2-259a69869d57";
        final Map<String, Integer> expectedProducts = Map.of(
                expectedProductId, 2);


        //when
        final TableRestaurant aTableRestaurant = TableRestaurant.newTable(false);
        aTableRestaurant.addProducts(expectedProducts);

        //then
        assertAll("Verify attributes table", () -> {
            assertNotNull(aTableRestaurant);
            assertNotNull(aTableRestaurant.getId());
            assertEquals(expectedReserved, aTableRestaurant.isReserved());
            assertEquals(1, aTableRestaurant.getProducts().size());
            assertEquals(2, aTableRestaurant.getProducts().get(expectedProductId));
        });
    }

    @Test
    void givenAnInvalidProductsLessThenZero_whenCallsAddProducts_thenReturnTable() {
        //given
        final boolean expectedReserved = false;

        final String expectedProductId = "270a78a4-1467-472f-a4c2-259a69869d57";
        final Map<String, Integer> expectedProducts = Map.of(
                expectedProductId, -1);


        //when
        final TableRestaurant aTableRestaurant = TableRestaurant.newTable(expectedReserved);
        aTableRestaurant.addProducts(expectedProducts);

        //then
        assertAll("Verify attributes table", () -> {
            assertNotNull(aTableRestaurant);
            assertNotNull(aTableRestaurant.getId());
            assertEquals(expectedReserved, aTableRestaurant.isReserved());
            assertEquals(0, aTableRestaurant.getProducts().size());
        });
    }

    @Test
    void givenAnInvalidProductsId_whenCallsAddProducts_thenReturnTable() {
        //given
        final boolean expectedReserved = false;

        final String expectedProductId = "";
        final Map<String, Integer> expectedProducts = Map.of(
                expectedProductId, 10);


        //when
        final TableRestaurant aTableRestaurant = TableRestaurant.newTable(expectedReserved);
        aTableRestaurant.addProducts(expectedProducts);

        //then
        assertAll("Verify attributes table", () -> {
            assertNotNull(aTableRestaurant);
            assertNotNull(aTableRestaurant.getId());
            assertEquals(expectedReserved, aTableRestaurant.isReserved());
            assertEquals(0, aTableRestaurant.getProducts().size());
        });
    }

}