package com.juliomesquita.order.domain.entities;

import com.juliomesquita.order.domain.enums.OrderStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Order-Domain-Test")
class OrderTest {

    @Test
    void givenAValidParams_whenCallsNewOrder_thenReturnOrder() {
        //given
        final String expectedTableId = UUID.randomUUID().toString();
        final String expectedProductId = UUID.randomUUID().toString();
        final OrderStatus expectedStatus = OrderStatus.CREATED;
        final Map<String, Integer> products = Map.of(expectedProductId, 2);

        //when
        final Order anOrder = Order.newOrder(expectedTableId, products);

        //then
        assertAll("Verify attributes of order", () -> {
            assertNotNull(anOrder);
            assertNotNull(anOrder.getId());
            assertEquals(expectedTableId, anOrder.getTableId());
            assertEquals(1, anOrder.getProducts().size());
            assertEquals(2, anOrder.getProducts().get(expectedProductId));
            assertNotNull(anOrder.getCreatedAt());
            assertNotNull(anOrder.getUpdatedAt());
            assertEquals(expectedStatus, anOrder.getStatus());
        });
    }

    @Test
    void givenAnInvalidParamTableIdNull_whenCallsNewOrder_thenReturnException() {
        //given
        final String expectedMessageError = "'Table id' should be not null";

        //when
        final IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> Order.newOrder(null, new HashMap<>()));

        //then
        assertEquals(expectedMessageError, exception.getMessage());
    }

    @Test
    void givenAnInvalidParamTableIdBlank_whenCallsNewOrder_thenReturnException() {
        //given
        final String expectedMessageError = "'Table id' should be not blank";

        //when
        final IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> Order.newOrder("", new HashMap<>()));

        //then
        assertEquals(expectedMessageError, exception.getMessage());
    }

    @Test
    void givenAnInvalidParamProductLessThanZero_whenCallsNewOrder_thenReturnException() {
        //given
        final String expectedMessageError = "Quantity of products must be greater than zero";

        //when
        final IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> Order.newOrder(UUID.randomUUID().toString(), new HashMap<>()));

        //then
        assertEquals(expectedMessageError, exception.getMessage());
    }

    @Test
    void givenAValidParams_whenCallsChangeStatus_thenReturnOrder() {
        //given
        final String expectedTableId = UUID.randomUUID().toString();
        final String expectedProductId = UUID.randomUUID().toString();
        final OrderStatus expectedStatus = OrderStatus.PREPARED;
        final Map<String, Integer> products = Map.of(expectedProductId, 2);
        final Order anOrder = Order.newOrder(expectedTableId, products);

        //when
        anOrder.changeStatus(expectedStatus);

        //then
        assertAll("Verify attributes of order", () -> {
            assertNotNull(anOrder);
            assertNotNull(anOrder.getId());
            assertEquals(expectedTableId, anOrder.getTableId());
            assertEquals(1, anOrder.getProducts().size());
            assertEquals(2, anOrder.getProducts().get(expectedProductId));
            assertNotNull(anOrder.getCreatedAt());
            assertNotNull(anOrder.getUpdatedAt());
            assertEquals(expectedStatus, anOrder.getStatus());
        });
    }
}