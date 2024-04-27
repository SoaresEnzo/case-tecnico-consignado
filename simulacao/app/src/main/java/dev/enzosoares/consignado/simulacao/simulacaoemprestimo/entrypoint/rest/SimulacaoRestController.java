package dev.enzosoares.consignado.simulacao.simulacaoemprestimo.entrypoint.rest;

import dev.enzosoares.consignado.simulacao.simulacaoemprestimo.entrypoint.rest.requests.SimularEmprestimoConsignadoRequest;
import dev.enzosoares.consignado.simulacao.simulacaoemprestimo.entrypoint.rest.responses.ListarSimulacoesResponseItem;
import dev.enzosoares.consignado.simulacao.simulacaoemprestimo.usecase.BuscarSimulacaoEmprestimoUseCase;
import dev.enzosoares.consignado.simulacao.simulacaoemprestimo.usecase.ListarSimulacoesUseCase;
import dev.enzosoares.consignado.simulacao.simulacaoemprestimo.usecase.SimularEmprestimoConsignadoUseCase;
import dev.enzosoares.consignado.simulacao.simulacaoemprestimo.usecase.dto.BuscarSimulacaoEmprestimoOutput;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
public class SimulacaoRestController implements SimulacaoRestEntrypoint {
    private final SimularEmprestimoConsignadoUseCase simularEmprestimoConsignadoUseCase;
    private final ListarSimulacoesUseCase listarSimulacoesUseCase;
    private final BuscarSimulacaoEmprestimoUseCase buscarSimulacaoEmprestimoUseCase;

    public SimulacaoRestController(
            final SimularEmprestimoConsignadoUseCase simularEmprestimoConsignadoUseCase,
            final ListarSimulacoesUseCase listarSimulacoesUseCase,
            final BuscarSimulacaoEmprestimoUseCase buscarSimulacaoEmprestimoUseCase
    ) {
        this.simularEmprestimoConsignadoUseCase = Objects.requireNonNull(simularEmprestimoConsignadoUseCase);
        this.listarSimulacoesUseCase = Objects.requireNonNull(listarSimulacoesUseCase);
        this.buscarSimulacaoEmprestimoUseCase = Objects.requireNonNull(buscarSimulacaoEmprestimoUseCase);
    }

    @Override
    public ResponseEntity<?> simularEmprestimoConsignado(SimularEmprestimoConsignadoRequest input) {
        return ResponseEntity.created(null)
                .body(this.simularEmprestimoConsignadoUseCase
                        .simular(SimularEmprestimoConsignadoRequest.toUseCaseInput(input))
                );
    }

    @Override
    public ResponseEntity<List<ListarSimulacoesResponseItem>> listarSimulacoes() {
        return ResponseEntity.ok(
                this.listarSimulacoesUseCase
                        .listar()
                        .stream()
                        .map(ListarSimulacoesResponseItem::from)
                        .toList());
    }

    @Override
    public ResponseEntity<BuscarSimulacaoEmprestimoOutput> buscarSimulacaoPorId(String id) {
        return ResponseEntity.ok(this.buscarSimulacaoEmprestimoUseCase.buscar(id));
    }
}
