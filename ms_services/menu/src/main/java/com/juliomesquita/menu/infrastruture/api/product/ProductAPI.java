package com.juliomesquita.menu.infrastruture.api.product;

import com.juliomesquita.menu.domain.commom.Pagination;
import com.juliomesquita.menu.infrastruture.api.product.models.ListProductsResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping(value = "/products")
@Tag(name = "Products")
public interface ProductAPI {
    @Operation(
            operationId = "ListProducts",
            summary = "Lista todos os Produtos do cardápio.",
            description = "Endpoint lista todos os endpoints do cardápio com filtros por nome, descrição e categoria.",
            tags = {"Products"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful operation", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ListProductsResponse.class))}),
                    @ApiResponse(responseCode = "400", description = "Bad Request."),
                    @ApiResponse(responseCode = "500", description = "Internal server error.")})
    @GetMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Pagination<ListProductsResponse>> ListProducts(
            @RequestParam(name = "search", required = false, defaultValue = "") final String search,
            @RequestParam(name = "currentPage", required = false, defaultValue = "0") final int currentPage,
            @RequestParam(name = "itemsPerPage", required = false, defaultValue = "10") final int itemsPerPage,
            @RequestParam(name = "sort", required = false, defaultValue = "name") final String sort,
            @RequestParam(name = "direction", required = false, defaultValue = "asc") final String direction);
}
