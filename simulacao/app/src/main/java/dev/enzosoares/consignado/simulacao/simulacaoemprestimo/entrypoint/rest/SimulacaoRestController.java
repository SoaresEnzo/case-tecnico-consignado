package dev.enzosoares.consignado.simulacao.simulacaoemprestimo.entrypoint.rest;

import dev.enzosoares.consignado.simulacao.simulacaoemprestimo.entrypoint.rest.requests.SimularEmprestimoConsignadoRequest;
import dev.enzosoares.consignado.simulacao.simulacaoemprestimo.entrypoint.rest.responses.ListarSimulacoesResponseItem;
import dev.enzosoares.consignado.simulacao.simulacaoemprestimo.usecase.ListarSimulacoesUseCase;
import dev.enzosoares.consignado.simulacao.simulacaoemprestimo.usecase.SimularEmprestimoConsignadoUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
public class SimulacaoRestController implements SimulacaoRestEntrypoint {
    private final SimularEmprestimoConsignadoUseCase simularEmprestimoConsignadoUseCase;
    private final ListarSimulacoesUseCase listarSimulacoesUseCase;

    public SimulacaoRestController(
            final SimularEmprestimoConsignadoUseCase simularEmprestimoConsignadoUseCase,
            final ListarSimulacoesUseCase listarSimulacoesUseCase
    ) {
        this.simularEmprestimoConsignadoUseCase = Objects.requireNonNull(simularEmprestimoConsignadoUseCase);
        this.listarSimulacoesUseCase = Objects.requireNonNull(listarSimulacoesUseCase);
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
}
