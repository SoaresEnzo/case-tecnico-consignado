package dev.enzosoares.consignado.simulacao.simulacaoemprestimo.entrypoint.rest;

import dev.enzosoares.consignado.simulacao.simulacaoemprestimo.entrypoint.rest.requests.SimularEmprestimoConsignadoRequest;
import dev.enzosoares.consignado.simulacao.simulacaoemprestimo.entrypoint.rest.responses.ListarSimulacoesResponseItem;
import dev.enzosoares.consignado.simulacao.simulacaoemprestimo.usecase.dto.BuscarSimulacaoEmprestimoOutput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("api/v1/simulacoes")
@Tag(name = "Simulação", description = "API de simulação de empréstimo consignado")
public interface SimulacaoRestEntrypoint {
    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Simular empréstimo consignado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Simulação realizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na simulação"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno")
    })
    ResponseEntity<?> simularEmprestimoConsignado(
            @RequestBody SimularEmprestimoConsignadoRequest input
    );

    @GetMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Listar simulações de empréstimo consignado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Simulações listadas com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno")
    })
    ResponseEntity<List<ListarSimulacoesResponseItem>> listarSimulacoes();

    @GetMapping(
            path = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Buscar simulação de empréstimo consignado por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Simulação encontrada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Simulação não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno")
    })
    ResponseEntity<BuscarSimulacaoEmprestimoOutput> buscarSimulacaoPorId(
            @PathVariable("id") String id
    );
}
