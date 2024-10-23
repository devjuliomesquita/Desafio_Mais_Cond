package com.juliomesquita.order.application.usecase.order.retrive.getbyid;

import com.juliomesquita.order.application.usecase.order.changestatus.ChangeStatusCommand;
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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DefaultGetOrderByIdUseCaseTest {
    @InjectMocks
    private DefaultGetOrderByIdUseCase useCase;
    @Mock
    private OrderGateway orderGateway;

    @BeforeEach
    void cleanUp(){
        Mockito.reset(orderGateway);
    }

    @Test
    void givenAValidParamId_whenGetOrderById_thenReturnOrder(){
        //given
        final String expectedTableId = UUID.randomUUID().toString();
        final String expectedProductId = UUID.randomUUID().toString();
        final int expectedProductCount = 2;
        final Map<String, Integer> expectedProducts = Map.of(expectedProductId, expectedProductCount);
        final Order anOrder = Order.newOrder(expectedTableId, expectedProducts);
        final String expectedOrderId = anOrder.getId();

        //when
        when(this.orderGateway.getById(any())).thenReturn(Optional.of(anOrder));
        final OrderOutput execute = this.useCase.execute(expectedOrderId);

        //then
        assertAll("Verify attributes of Order", () ->{
            assertNotNull(execute);
            assertEquals(anOrder.getId(), execute.id());
            assertEquals(anOrder.getTableId(), execute.tableId());
            assertEquals(anOrder.getProducts(), execute.products());
            assertEquals(anOrder.getStatus(), execute.status());
            assertEquals(anOrder.getCreatedAt(), execute.createdAt());
            assertEquals(anOrder.getUpdatedAt(), execute.updatedAt());
        });
    }

    @Test
    void givenAnInvalidParamId_whenGetOrderById_thenReturnNotFound(){
        //given
        final String expectedOrderId = "c9900fd7-c21e-410b-bbda-92890974a720";
        final String expectedMessageError = "'Order' with id :: c9900fd7-c21e-410b-bbda-92890974a720 not found";

        //when
        when(this.orderGateway.getById(any())).thenReturn(Optional.empty());

        //then
        final RuntimeException exception =
                assertThrows(RuntimeException.class, () -> this.useCase.execute(expectedOrderId));
        assertEquals(expectedMessageError, exception.getMessage());
    }

    @Test
    void givenAnInvalidParamId_whenGetOrderById_thenReturnException(){
        //given
        final String expectedOrderId = "c9900fd7-c21e-410b-bbda-92890974a720";
        final String expectedMessageError = "Error Gateway";

        //when
        when(this.orderGateway.getById(any())).thenThrow(new IllegalStateException(expectedMessageError));

        //then
        final IllegalStateException exception =
                assertThrows(IllegalStateException.class, () -> this.useCase.execute(expectedOrderId));
        assertEquals(expectedMessageError, exception.getMessage());
    }

}