package com.juliomesquita.menu.application.usecases.table.addproducts;

import com.juliomesquita.menu.domain.table.TableRestaurant;
import com.juliomesquita.menu.domain.table.TableRestaurantGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class DefaultAddProductsUseCaseTest {
    @InjectMocks
    private DefaultAddProductsUseCase useCase;
    @Mock
    private TableRestaurantGateway tableRestaurantGateway;

    @BeforeEach
    void cleanUp() {
        Mockito.reset(tableRestaurantGateway);
    }

    /*
     * 1. Caso feliz
     * 2. Caso Enviando um Id de mesa errado
     * 3. Gateway erro
     * */
    @Test
    void givenAValidParams_whenCallsAddProducts_thenReturnTableId() {
        //given
        final String expectedProductId = "1a17b962-fbb4-4024-a3ce-c3fbb65a11fd";
        final boolean expectedReserved = true;
        final TableRestaurant aTable = TableRestaurant.newTable(false);
        final String expectedTableId = aTable.getId();
        final Map<String, Integer> mapProducts = Map.of(
                expectedProductId, 2);
        final AddProductsCommand aCommand = new AddProductsCommand(expectedTableId, mapProducts);

        //when
        when(this.tableRestaurantGateway.findById(any())).thenReturn(Optional.of(aTable));
        when(this.tableRestaurantGateway.save(any())).thenReturn(expectedTableId);
        final String response = this.useCase.execute(aCommand);

        //then
        assertAll("Verify response", () -> {
            assertNotNull(response);
            assertEquals(expectedTableId, response);
        });
        verify(this.tableRestaurantGateway, times(1)).save(argThat(
                aTableRestaurant -> {
                    return Objects.equals(expectedTableId, aTableRestaurant.getId()) &&
                            Objects.equals(1, aTableRestaurant.getProducts().size()) &&
                            Objects.equals(2, aTableRestaurant.getProducts().get(expectedProductId)) &&
                            Objects.equals(expectedReserved, aTableRestaurant.isReserved());
                }
        ));
    }

    @Test
    void givenAnInvalidParamTableId_whenCallsAddProducts_thenReturnNotFound() {
        //given
        final String expectedTableId = "1a17b962-fbb4-4024-a3ce-c3fbb65a11fd";
        final String expectedMessageError = "'Table' with id :: 1a17b962-fbb4-4024-a3ce-c3fbb65a11fd not found";
        final AddProductsCommand aCommand = new AddProductsCommand(expectedTableId, new HashMap<>());

        //when
        when(this.tableRestaurantGateway.findById(any())).thenReturn(Optional.empty());

        //then
        final RuntimeException exception =
                assertThrows(RuntimeException.class, () -> this.useCase.execute(aCommand));
        assertEquals(expectedMessageError, exception.getMessage());
        verify(this.tableRestaurantGateway, times(1)).findById(any());
        verify(this.tableRestaurantGateway, times(0)).save(any());
    }

    @Test
    void givenAValidParams_whenCallsAddProducts_thenAnyException() {
        //given
        final String expectedTableId = "1a17b962-fbb4-4024-a3ce-c3fbb65a11fd";
        final String expectedMessageError = "gatewayError";
        final AddProductsCommand aCommand = new AddProductsCommand(expectedTableId, new HashMap<>());

        //when
        when(this.tableRestaurantGateway.findById(any())).thenThrow(new IllegalStateException(expectedMessageError));

        //then
        final IllegalStateException exception =
                assertThrows(IllegalStateException.class, () -> this.useCase.execute(aCommand));
        assertEquals(expectedMessageError, exception.getMessage());
        verify(this.tableRestaurantGateway, times(1)).findById(any());
        verify(this.tableRestaurantGateway, times(0)).save(any());
    }

}