package com.juliomesquita.order.infrastructure.api;

import com.juliomesquita.order.infrastructure.api.models.ListOrdersResponse;
import com.juliomesquita.order.infrastructure.api.models.OrderResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
@RequestMapping(value = "/order")
@Tag(name = "Order")
public interface OrderAPI {
    @Operation(
            operationId = "listOrders",
            summary = "Lista todos os pedidos da criados.",
            description = "Endpoint lista todos os pedidos criados.",
            tags = {"Order"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful operation", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ListOrdersResponse.class))}),
                    @ApiResponse(responseCode = "400", description = "Bad Request."),
                    @ApiResponse(responseCode = "500", description = "Internal server error.")})
    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<ListOrdersResponse>> listOrders();


    @Operation(
            operationId = "getOrderById",
            summary = "Buscar um pedido pelo id.",
            description = "Endpoint para busca um pedido pelo id e retornar suas caracteríticas.",
            tags = {"Order"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful operation", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = OrderResponse.class))}),
                    @ApiResponse(responseCode = "400", description = "Bad Request."),
                    @ApiResponse(responseCode = "500", description = "Internal server error.")})
    @GetMapping(
            value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<OrderResponse> getOrderById(@PathVariable(name = "id") String id);
}
