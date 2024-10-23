package com.juliomesquita.order.application.usecase.order.retrive.list;

import com.juliomesquita.order.domain.OrderGateway;
import com.juliomesquita.order.domain.entities.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DefaultListOrderUseCaseTest {
    @InjectMocks
    private DefaultListOrderUseCase useCase;
    @Mock
    private OrderGateway orderGateway;

    @BeforeEach
    void cleanUp(){
        Mockito.reset(orderGateway);
    }

    @Test
    void given_whenCallsListOrders_thenReturnListOrders(){
        //given
        final String expectedTableId = UUID.randomUUID().toString();
        final String expectedProductId = UUID.randomUUID().toString();
        final int expectedProductQuantity = 1;
        final Order anOrder = Order.newOrder(expectedTableId, Map.of(expectedProductId, expectedProductQuantity));

        //when
        when(this.orderGateway.getAll()).thenReturn(List.of(anOrder));
        final List<ListOrderOutput> execute = this.useCase.execute();

        //then
        assertAll("Verify attributes od list orders", () ->{
            assertNotNull(execute);
            assertEquals(1, execute.size());
            assertEquals(anOrder.getId(), execute.get(0).id());
            assertEquals(anOrder.getCreatedAt(), execute.get(0).createdAt());
            assertEquals(anOrder.getStatus(), execute.get(0).status());
            assertEquals(expectedTableId, execute.get(0).tableId());
        });
    }

    @Test
    void given_whenCallsListOrders_thenReturnListEmpty(){

        //when
        when(this.orderGateway.getAll()).thenReturn(List.of());
        final List<ListOrderOutput> execute = this.useCase.execute();

        //then
        assertAll("Verify attributes od list orders", () ->{
            assertNotNull(execute);
            assertEquals(0, execute.size());
        });
    }

    @Test
    void given_whenCallsListOrders_thenReturnAnyException(){
        //given
        final String expectedMessageError = "Gateway Error";

        //when
        when(this.orderGateway.getAll()).thenThrow(new IllegalStateException(expectedMessageError));
        final IllegalStateException exception = assertThrows(IllegalStateException.class, () -> this.useCase.execute());

        //then
        assertEquals(expectedMessageError, exception.getMessage());
    }

}