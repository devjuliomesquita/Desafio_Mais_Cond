package com.juliomesquita.order.application.usecase.order.create;

import com.juliomesquita.order.domain.OrderGateway;
import com.juliomesquita.order.domain.SentEventService;
import com.juliomesquita.order.domain.entities.Order;
import com.juliomesquita.order.domain.enums.OrderStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class DefaultCreateOrderUseCaseTest {
    @InjectMocks
    private DefaultCreateOrderUseCase useCase;
    @Mock
    private OrderGateway orderGateway;
    @Mock
    private SentEventService<CreateComandaMessage> sentEventService;

    @BeforeEach
    void cleanUp() {
        Mockito.reset(orderGateway);
    }

    @Test
    void givenAValidParams_whenCallsCreateOrder_thenReturnOrderId() {
        //given
        final String expectedTableId = UUID.randomUUID().toString();
        final String expectedProductId = UUID.randomUUID().toString();
        final int expectedProductCount = 2;
        final Map<String, Integer> expectedProducts = Map.of(expectedProductId, expectedProductCount);
        final CreateOrderCommand aCommand = new CreateOrderCommand(expectedTableId, expectedProducts);

        //when
        doNothing().when(this.sentEventService).sentEvent(any());
        when(this.orderGateway.save(any())).thenReturn(any(String.class));
        final CreateOrderOutput execute = this.useCase.execute(aCommand);

        //then
        assertNotNull(execute);
        verify(this.sentEventService, times(1)).sentEvent(any());
        verify(this.orderGateway, times(1)).save(any());
    }

    @Test
    void givenAnInvalidParamsTableIdNull_whenCallsCreateOrder_thenReturnException() {
        //given
        final String expectedTableId = null;
        final String expectedProductId = UUID.randomUUID().toString();
        final String expectedMessageError = "'Table id' should be not null";
        final int expectedProductCount = 2;
        final Map<String, Integer> expectedProducts = Map.of(expectedProductId, expectedProductCount);
        final CreateOrderCommand aCommand = new CreateOrderCommand(expectedTableId, expectedProducts);

        //when
        final IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> this.useCase.execute(aCommand));

        //then
        assertEquals(expectedMessageError, exception.getMessage());
        verify(this.sentEventService, times(0)).sentEvent(any());
        verify(this.orderGateway, times(0)).save(any());
    }

    @Test
    void givenAnInvalidParamsTableIdBlank_whenCallsCreateOrder_thenReturnException() {
        //given
        final String expectedTableId = "";
        final String expectedProductId = UUID.randomUUID().toString();
        final int expectedProductCount = 2;
        final String expectedMessageError = "'Table id' should be not blank";
        final Map<String, Integer> expectedProducts = Map.of(expectedProductId, expectedProductCount);
        final CreateOrderCommand aCommand = new CreateOrderCommand(expectedTableId, expectedProducts);

        //when
        final IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> this.useCase.execute(aCommand));

        //then
        assertEquals(expectedMessageError, exception.getMessage());
        verify(this.sentEventService, times(0)).sentEvent(any());
        verify(this.orderGateway, times(0)).save(any());
    }

    @Test
    void givenAnInvalidParamsListProductQuantityLessThenZero_whenCallsCreateOrder_thenReturnException() {
        //given
        final String expectedTableId = UUID.randomUUID().toString();
        final String expectedMessageError = "Quantity of products must be greater than zero";
        final Map<String, Integer> expectedProducts = Map.of();
        final CreateOrderCommand aCommand = new CreateOrderCommand(expectedTableId, expectedProducts);

        //when
        final IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> this.useCase.execute(aCommand));

        //then
        assertEquals(expectedMessageError, exception.getMessage());
        verify(this.sentEventService, times(0)).sentEvent(any());
        verify(this.orderGateway, times(0)).save(any());
    }

    @Test
    void givenAnInvalidParamsProductQuantityLessThenZero_whenCallsCreateOrder_thenReturnException() {
        //given
        final String expectedTableId = UUID.randomUUID().toString();
        final String expectedMessageError = "Quantity of product must be greater than zero";
        final String expectedProductId = UUID.randomUUID().toString();
        final int expectedProductCount = 0;
        final Map<String, Integer> expectedProducts = Map.of(expectedProductId, expectedProductCount);
        final CreateOrderCommand aCommand = new CreateOrderCommand(expectedTableId, expectedProducts);

        //when
        final IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> this.useCase.execute(aCommand));

        //then
        assertEquals(expectedMessageError, exception.getMessage());
        verify(this.sentEventService, times(0)).sentEvent(any());
        verify(this.orderGateway, times(0)).save(any());
    }

    @Test
    void givenAValidParamsEvent_whenCallsCreateOrder_thenReturnException() {
        //given
        final String expectedTableId = UUID.randomUUID().toString();
        final String expectedMessageError = "Gateway Error";
        final String expectedProductId = UUID.randomUUID().toString();
        final int expectedProductCount = 1;
        final Map<String, Integer> expectedProducts = Map.of(expectedProductId, expectedProductCount);
        final CreateOrderCommand aCommand = new CreateOrderCommand(expectedTableId, expectedProducts);

        //when
        doThrow(new IllegalStateException(expectedMessageError)).when(this.sentEventService).sentEvent(any());

        //then
        final IllegalStateException exception =
                assertThrows(IllegalStateException.class, () -> this.useCase.execute(aCommand));
        assertEquals(expectedMessageError, exception.getMessage());
        verify(this.sentEventService, times(1)).sentEvent(any());
        verify(this.orderGateway, times(0)).save(any());
    }

    @Test
    void givenAValidParams_whenCallsCreateOrder_thenReturnException() {
        //given
        final String expectedTableId = UUID.randomUUID().toString();
        final String expectedMessageError = "Gateway Error";
        final String expectedProductId = UUID.randomUUID().toString();
        final int expectedProductCount = 1;
        final Map<String, Integer> expectedProducts = Map.of(expectedProductId, expectedProductCount);
        final CreateOrderCommand aCommand = new CreateOrderCommand(expectedTableId, expectedProducts);

        //when
        doNothing().when(this.sentEventService).sentEvent(any());
        when(this.orderGateway.save(any())).thenThrow(new IllegalStateException(expectedMessageError));

        //then
        final IllegalStateException exception =
                assertThrows(IllegalStateException.class, () -> this.useCase.execute(aCommand));
        assertEquals(expectedMessageError, exception.getMessage());
        verify(this.sentEventService, times(1)).sentEvent(any());
        verify(this.orderGateway, times(1)).save(any());
    }


}