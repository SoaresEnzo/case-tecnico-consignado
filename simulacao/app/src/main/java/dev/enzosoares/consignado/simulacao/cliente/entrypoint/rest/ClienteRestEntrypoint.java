package dev.enzosoares.consignado.simulacao.cliente.entrypoint.rest;

import dev.enzosoares.consignado.simulacao.cliente.entrypoint.rest.responses.ListarClientesResponseItem;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RequestMapping("/api/v1/clientes")
@Tag(name = "Clientes", description = "API de clientes do empréstimo consignado")
public interface ClienteRestEntrypoint {

    @GetMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Listar clientes do empréstimo consignado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Clientes listados com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno")
    })
    ResponseEntity<List<ListarClientesResponseItem>> listarClientes();
}
