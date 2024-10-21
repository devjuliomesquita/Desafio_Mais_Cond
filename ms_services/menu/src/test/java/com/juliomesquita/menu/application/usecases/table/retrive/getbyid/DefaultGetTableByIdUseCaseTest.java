package com.juliomesquita.menu.application.usecases.table.retrive.getbyid;

import com.juliomesquita.menu.domain.table.TableRestaurant;
import com.juliomesquita.menu.domain.table.TableRestaurantGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DefaultGetTableByIdUseCaseTest {
    @InjectMocks
    private DefaultGetTableByIdUseCase useCase;
    @Mock
    private TableRestaurantGateway tableRestaurantGateway;

    @BeforeEach
    void cleanUp() {
        Mockito.reset(tableRestaurantGateway);
    }


    @Test
    void givenAValidIdTable_whenCallsGetTableById_thenReturnTable() {
        //given
        final boolean expectedReserved = true;
        final TableRestaurant aTable = TableRestaurant.newTable(expectedReserved);
        final String expectedTableId = aTable.getId();

        //when
        when(this.tableRestaurantGateway.findById(expectedTableId)).thenReturn(Optional.of(aTable));
        final TableOutput response = this.useCase.execute(expectedTableId);

        //then
        assertAll("Verify attributes of tableRestaurant", () -> {
            assertNotNull(response);
            assertEquals(expectedTableId, response.id());
            assertEquals(expectedReserved, response.reserved());
            assertNotNull(response.products());
            assertEquals(0, response.products().size());
        });
        verify(this.tableRestaurantGateway, times(1)).findById(anyString());
    }

    @Test
    void givenAnInvalidIdTable_whenCallsGetTableById_thenReturnNotFound() {
        //given
        final String expectedTableId = "06459528-2930-4c54-b477-f5c7b15ab4ba";
        final String expectedMessageError = "'Table' with id :: 06459528-2930-4c54-b477-f5c7b15ab4ba not found";

        //when
        when(this.tableRestaurantGateway.findById(expectedTableId)).thenReturn(Optional.empty());

        //then
        final RuntimeException exception =
                assertThrows(RuntimeException.class, () -> this.useCase.execute(expectedTableId));
        assertEquals(expectedMessageError, exception.getMessage());
        verify(this.tableRestaurantGateway, times(1)).findById(expectedTableId);
    }

    @Test
    void givenAValidIdTable_whenCallsGetTableById_thenReturnAnyException() {
        //given
        final String expectedTableId = "06459528-2930-4c54-b477-f5c7b15ab4ba";
        final String expectedMessageError = "Gateway Error";

        //when
        when(this.tableRestaurantGateway.findById(expectedTableId))
                .thenThrow(new IllegalStateException(expectedMessageError));

        //then
        final RuntimeException exception =
                assertThrows(RuntimeException.class, () -> this.useCase.execute(expectedTableId));
        assertEquals(expectedMessageError, exception.getMessage());
        verify(this.tableRestaurantGateway, times(1)).findById(expectedTableId);
    }

}