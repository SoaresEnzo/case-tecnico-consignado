package dev.enzosoares.consignado.custodia.contrato.usecase.impl;

import dev.enzosoares.consignado.custodia.contrato.Contrato;
import dev.enzosoares.consignado.custodia.contrato.dataprovider.ContratoRepository;
import dev.enzosoares.consignado.custodia.contrato.usecase.CustodiarContratoUseCase;
import dev.enzosoares.consignado.custodia.contrato.usecase.dto.CustodiarContratoUseCaseInput;
import dev.enzosoares.consignado.custodia.contrato.usecase.dto.CustodiarContratoUseCaseOutput;
import dev.enzosoares.consignado.custodia.errors.NotFoundException;
import dev.enzosoares.consignado.custodia.simulacaoemprestimo.facade.SimulacaoEmprestimoFacade;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.Objects;
import java.util.UUID;

@Named
public class CustodiarContratoUseCaseImpl implements CustodiarContratoUseCase {
    private final ContratoRepository contratoRepository;
    private final SimulacaoEmprestimoFacade simulacaoEmprestimoFacade;

    @Inject
    public CustodiarContratoUseCaseImpl(
            final ContratoRepository contratoRepository,
            final SimulacaoEmprestimoFacade simulacaoEmprestimoFacade
    ) {
        this.contratoRepository = Objects.requireNonNull(contratoRepository);
        this.simulacaoEmprestimoFacade = Objects.requireNonNull(simulacaoEmprestimoFacade);
    }

    @Override
    public CustodiarContratoUseCaseOutput custodiar(CustodiarContratoUseCaseInput input) {
        final var idSimulacao = input.idSimulacao();
        final var simulacao = this.simulacaoEmprestimoFacade
                .fetchById(UUID.fromString(idSimulacao))
                .orElseThrow(() -> new NotFoundException("Não foi possível buscar a simulação."));

        final var contratoCustodiado = Contrato.with(simulacao.getIdSimulacao().toString());
        this.contratoRepository.save(contratoCustodiado);
        return CustodiarContratoUseCaseOutput.from(contratoCustodiado);
    }
}
