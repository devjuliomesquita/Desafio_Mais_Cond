package com.juliomesquita.preparation.infrastructure.api;

import com.juliomesquita.preparation.infrastructure.api.models.ComandaResponse;
import com.juliomesquita.preparation.infrastructure.api.models.ListComandasResponse;
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

@RequestMapping(value = "/comanda")
@Tag(name = "Comanda")
public interface ComandaAPI {
    @Operation(
            operationId = "listComandas",
            summary = "Lista todas as comandas que chegaram.",
            description = "Endpoint lista todos as comandas criadas.",
            tags = {"Comanda"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful operation", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ListComandasResponse.class))}),
                    @ApiResponse(responseCode = "400", description = "Bad Request."),
                    @ApiResponse(responseCode = "500", description = "Internal server error.")})
    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<ListComandasResponse>> listComandas();


    @Operation(
            operationId = "getComandaById",
            summary = "Buscar uma comanda pelo id.",
            description = "Endpoint para busca uma comanda pelo id e retornar suas caracteríticas.",
            tags = {"Comanda"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful operation", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ComandaResponse.class))}),
                    @ApiResponse(responseCode = "400", description = "Bad Request."),
                    @ApiResponse(responseCode = "500", description = "Internal server error.")})
    @GetMapping(
            value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ComandaResponse> getComandaById(@PathVariable(name = "id") String id);

    @Operation(
            operationId = "closeComandaById",
            summary = "Fechar uma comanda pelo id.",
            description = "Endpoint para busca uma comanda pelo id e finaliza a mesma.",
            tags = {"Comanda"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful operation"),
                    @ApiResponse(responseCode = "400", description = "Bad Request."),
                    @ApiResponse(responseCode = "500", description = "Internal server error.")})
    @GetMapping(
            value = "/{id}/close")
    ResponseEntity<?> closeComandaById(@PathVariable(name = "id") String id);
}
