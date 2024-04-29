package dev.enzosoares.consignado.custodia.contrato.entrypoint.rest;

import dev.enzosoares.consignado.custodia.contrato.entrypoint.rest.requests.CustodiarContratoInput;
import dev.enzosoares.consignado.custodia.contrato.entrypoint.rest.responses.CustodiarContratoResponse;
import dev.enzosoares.consignado.custodia.contrato.entrypoint.rest.responses.ListarContratoResponseItem;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("api/v1/contratos")
@Tag(name = "Contrato", description = "API de custódia de contrato de empréstimo consignado")
public interface ContratoRestEndpoint {

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Custodiar contrato de empréstimo consignado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Contrato custodiado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na custódia do contrato"),
            @ApiResponse(responseCode = "404", description = "Simulação não encontrada"),
            @ApiResponse(responseCode = "415", description = "Tipo de mídia não suportado"),
            @ApiResponse(responseCode = "500", description = "Erro interno")
    })
    ResponseEntity<CustodiarContratoResponse> custodiarContrato(
            @RequestBody CustodiarContratoInput input
    );

    @GetMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Listar contratos de empréstimo consignado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Contratos listados com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno")
    })
    ResponseEntity<List<ListarContratoResponseItem>> listarContratos();
}
