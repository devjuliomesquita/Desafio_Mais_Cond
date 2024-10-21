package com.juliomesquita.menu.application.usecases.table.retrive.list;

import com.juliomesquita.menu.domain.table.TableRestaurant;
import com.juliomesquita.menu.domain.table.TableRestaurantGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DefaultListTablesUseCaseTest {
    @InjectMocks
    private DefaultListTablesUseCase useCase;
    @Mock
    private TableRestaurantGateway tableRestaurantGateway;

    @BeforeEach
    void cleanUp() {
        Mockito.reset(tableRestaurantGateway);
    }

    @Test
    void whenCallsListTableRestaurant_thenReturnListTables() {
        //given
        final List<TableRestaurant> expectedTables = List.of(
                TableRestaurant.newTable(false),
                TableRestaurant.newTable(false),
                TableRestaurant.newTable(false),
                TableRestaurant.newTable(false)
        );
        final int expectedTablesCount = expectedTables.size();

        //when
        when(this.tableRestaurantGateway.findAll()).thenReturn(expectedTables);
        final List<ListTablesOutput> response = this.useCase.execute();

        //then
        assertNotNull(response);
        assertEquals(expectedTablesCount, response.size());
        verify(this.tableRestaurantGateway, times(1)).findAll();
    }

    @Test
    void whenCallsListTableRestaurant_thenReturnAnyException() {
        //given
        final String expectedMessageError = "Gateway Error";

        //when
        when(this.tableRestaurantGateway.findAll()).thenThrow(new IllegalStateException(expectedMessageError));

        //then
        final IllegalStateException exception =
                assertThrows(IllegalStateException.class, () -> this.useCase.execute());
        assertEquals(expectedMessageError, exception.getMessage());
        verify(this.tableRestaurantGateway, times(1)).findAll();
    }

    @Test
    void whenCallsListTableRestaurant_thenReturnEmptyList() {
        //given
        final List<TableRestaurant> expectedTables = List.of();
        final int expectedTablesCount = 0;

        //when
        when(this.tableRestaurantGateway.findAll()).thenReturn(expectedTables);
        final List<ListTablesOutput> response = this.useCase.execute();

        //then
        assertNotNull(response);
        assertEquals(expectedTablesCount, response.size());
        verify(this.tableRestaurantGateway, times(1)).findAll();
    }

}