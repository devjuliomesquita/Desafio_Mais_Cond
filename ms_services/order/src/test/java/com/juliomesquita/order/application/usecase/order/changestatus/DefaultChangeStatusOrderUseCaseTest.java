package com.juliomesquita.order.application.usecase.order.changestatus;

import ch.qos.logback.classic.spi.EventArgUtil;
import com.juliomesquita.order.application.usecase.order.create.CreateOrderCommand;
import com.juliomesquita.order.domain.OrderGateway;
import com.juliomesquita.order.domain.entities.Order;
import com.juliomesquita.order.domain.enums.OrderStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DefaultChangeStatusOrderUseCaseTest {
    @InjectMocks
    private DefaultChangeStatusOrderUseCase useCase;
    @Mock
    private OrderGateway orderGateway;

    @BeforeEach
    void cleanUp() {
        Mockito.reset(orderGateway);
    }

    @Test
    void givenAValidParam_whenCallsChangeStatus_thenReturnOrderId() {
        //given
        final String expectedTableId = UUID.randomUUID().toString();
        final String expectedProductId = UUID.randomUUID().toString();
        final int expectedProductCount = 2;
        final Map<String, Integer> expectedProducts = Map.of(expectedProductId, expectedProductCount);
        final Order anOrder = Order.newOrder(expectedTableId, expectedProducts);
        final ChangeStatusCommand aCommand = new ChangeStatusCommand(expectedTableId, OrderStatus.SENT);

        //when
        when(this.orderGateway.getById(any())).thenReturn(Optional.of(anOrder));
        when(this.orderGateway.save(any())).thenReturn(any(String.class));
        this.useCase.execute(aCommand);

        //then
        verify(this.orderGateway, times(1)).getById(any());
        verify(this.orderGateway, times(1)).save(any());
    }

    @Test
    void givenAnInvalidParamId_whenCallsChangeStatus_thenReturnException() {
        //given
        final String expectedTableId = "7ebe78cc-5126-465c-a632-a991a0284c9f";
        final String expectedMessageError = "'Order' with id :: 7ebe78cc-5126-465c-a632-a991a0284c9f not found";

        final ChangeStatusCommand aCommand = new ChangeStatusCommand(expectedTableId, OrderStatus.SENT);

        //when
        when(this.orderGateway.getById(any())).thenReturn(Optional.empty());
        final RuntimeException exception =
                assertThrows(RuntimeException.class, () -> this.useCase.execute(aCommand));

        //then
        assertEquals(expectedMessageError, exception.getMessage());
        verify(this.orderGateway, times(1)).getById(any());
        verify(this.orderGateway, times(0)).save(any());
    }

    @Test
    void givenAValidParams_whenCallsChangeStatus_thenReturnExceptionGatewayGet() {
        //given
        final String expectedTableId = "7ebe78cc-5126-465c-a632-a991a0284c9f";
        final String expectedMessageError = "Gateway Error";

        final ChangeStatusCommand aCommand = new ChangeStatusCommand(expectedTableId, OrderStatus.SENT);

        //when
        when(this.orderGateway.getById(any())).thenThrow(new IllegalStateException(expectedMessageError));
        final IllegalStateException exception =
                assertThrows(IllegalStateException.class, () -> this.useCase.execute(aCommand));

        //then
        assertEquals(expectedMessageError, exception.getMessage());
        verify(this.orderGateway, times(1)).getById(any());
        verify(this.orderGateway, times(0)).save(any());
    }

    @Test
    void givenAValidParams_whenCallsChangeStatus_thenReturnExceptionGatewaySave() {
        //given
        final String expectedTableId = "7ebe78cc-5126-465c-a632-a991a0284c9f";
        final String expectedProductId = UUID.randomUUID().toString();
        final String expectedMessageError = "Gateway Error";
        final int expectedProductCount = 2;
        final Map<String, Integer> expectedProducts = Map.of(expectedProductId, expectedProductCount);
        final Order anOrder = Order.newOrder(expectedTableId, expectedProducts);

        final ChangeStatusCommand aCommand = new ChangeStatusCommand(expectedTableId, OrderStatus.SENT);

        //when
        when(this.orderGateway.getById(any())).thenReturn(Optional.of(anOrder));
        when(this.orderGateway.save(any())).thenThrow(new IllegalStateException(expectedMessageError));
        final IllegalStateException exception =
                assertThrows(IllegalStateException.class, () -> this.useCase.execute(aCommand));

        //then
        assertEquals(expectedMessageError, exception.getMessage());
        verify(this.orderGateway, times(1)).getById(any());
        verify(this.orderGateway, times(1)).save(any());
    }

}