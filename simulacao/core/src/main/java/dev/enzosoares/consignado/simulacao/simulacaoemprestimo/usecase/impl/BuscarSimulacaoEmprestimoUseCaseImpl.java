package dev.enzosoares.consignado.simulacao.simulacaoemprestimo.usecase.impl;

import dev.enzosoares.consignado.simulacao.errors.NotFoundException;
import dev.enzosoares.consignado.simulacao.simulacaoemprestimo.dataprovider.SimulacaoEmprestimoRepository;
import dev.enzosoares.consignado.simulacao.simulacaoemprestimo.usecase.BuscarSimulacaoEmprestimoUseCase;
import dev.enzosoares.consignado.simulacao.simulacaoemprestimo.usecase.dto.BuscarSimulacaoEmprestimoOutput;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.Objects;

@Named
public class BuscarSimulacaoEmprestimoUseCaseImpl implements BuscarSimulacaoEmprestimoUseCase {
    private final SimulacaoEmprestimoRepository simulacaoEmprestimoRepository;

    @Inject
    public BuscarSimulacaoEmprestimoUseCaseImpl(
            final SimulacaoEmprestimoRepository simulacaoEmprestimoRepository
    ) {
        this.simulacaoEmprestimoRepository = Objects.requireNonNull(simulacaoEmprestimoRepository);
    }

    @Override
    public BuscarSimulacaoEmprestimoOutput buscar(String input) {
        final var simulacao = this.simulacaoEmprestimoRepository
                .findById(input)
                .orElseThrow(() -> new NotFoundException("Simulação não encontrada."));

        return BuscarSimulacaoEmprestimoOutput.from(simulacao);
    }
}
