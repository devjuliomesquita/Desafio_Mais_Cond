package com.juliomesquita.preparation.domain.entities;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Comanda-Domain-Test")
class ComandaTest {

    @Test
    void givenAValidParams_whenCallsNewComanda_thenReturnComanda() {
        //given
        final String expectedOrderId = UUID.randomUUID().toString();

        //when
        final Comanda aComanda = Comanda.newComanda(expectedOrderId);

        //then
        assertAll("verify attributes of Comanda", () -> {
            assertNotNull(aComanda);
            assertNotNull(aComanda.getId());
            assertEquals(expectedOrderId, aComanda.getOrderId());
            assertNotNull(aComanda.getCreatedAt());
            assertNotNull(aComanda.getUpdatedAt());
            assertFalse(aComanda.isClosed());
        });
    }

    @Test
    void givenAnInvalidParamOrderIdNull_whenCallsNewComanda_thenReturnException() {
        //given
        final String expectedOrderId = null;
        final String expectedMessageError = "'Order id' should be not null";

        //when
        final IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> Comanda.newComanda(expectedOrderId));

        //then
        assertEquals(expectedMessageError, exception.getMessage());
    }

    @Test
    void givenAnInvalidParamOrderIdBlank_whenCallsNewComanda_thenReturnException() {
        //given
        final String expectedOrderId = "";
        final String expectedMessageError = "'Order id' should be not blank";

        //when
        final IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> Comanda.newComanda(expectedOrderId));

        //then
        assertEquals(expectedMessageError, exception.getMessage());
    }

    @Test
    void givenAComandaValid_whenCallsCloseComanda_thenReturnComanda() {
        //given
        final String expectedOrderId = UUID.randomUUID().toString();
        final Comanda aComanda = Comanda.newComanda(expectedOrderId);

        //when
        final Comanda aComandaClosed = aComanda.closeComanda();
        //then
        assertAll("verify attributes of Comanda", () -> {
            assertNotNull(aComandaClosed);
            assertEquals(aComanda.getId(), aComandaClosed.getId());
            assertEquals(expectedOrderId, aComandaClosed.getOrderId());
            assertEquals(aComanda.getCreatedAt(), aComandaClosed.getCreatedAt());
            assertNotNull(aComandaClosed.getUpdatedAt());
            assertTrue(aComanda.isClosed());
        });
    }
}