package com.juliomesquita.menu.infrastruture.api.restaturant;

import com.juliomesquita.menu.domain.commom.Pagination;
import com.juliomesquita.menu.infrastruture.api.product.models.ListProductsResponse;
import com.juliomesquita.menu.infrastruture.api.restaturant.models.AddProductsRequest;
import com.juliomesquita.menu.infrastruture.api.restaturant.models.ListTableResponse;
import com.juliomesquita.menu.infrastruture.api.restaturant.models.TableResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/restaurant")
@Tag(name = "Restaurant")
public interface RestaurantAPI {
    @Operation(
            operationId = "listTablesRestaurant",
            summary = "Lista todas as mesas e suas disponibilidades.",
            description = "Endpoint lista todas as mesas do restaturante e suas disponibilidades.",
            tags = {"Restaurant"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful operation", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ListTableResponse.class))}),
                    @ApiResponse(responseCode = "400", description = "Bad Request."),
                    @ApiResponse(responseCode = "500", description = "Internal server error.")})
    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<ListTableResponse>> listTablesRestaurant();


    @Operation(
            operationId = "getTableById",
            summary = "Buscar uma mesa pelo id.",
            description = "Endpoint para busca uma mesa e seus atributos pelo id.",
            tags = {"Restaurant"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful operation", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = TableResponse.class))}),
                    @ApiResponse(responseCode = "400", description = "Bad Request."),
                    @ApiResponse(responseCode = "500", description = "Internal server error.")})
    @GetMapping(
            value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<TableResponse> getTableById(@PathVariable(name = "id") String id);

    @Operation(
            operationId = "addProducts",
            summary = "Adiciona uma lista de produtos a mesa.",
            description = "Endpoint para adicionar um novo produto ou uma lista de novos produtos ao consumo da mesa.",
            tags = {"Restaurant"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful operation", content = {
                            @Content(mediaType = "application/json")}),
                    @ApiResponse(responseCode = "400", description = "Bad Request."),
                    @ApiResponse(responseCode = "500", description = "Internal server error.")})
    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> addProducts(@RequestBody AddProductsRequest input);
}
