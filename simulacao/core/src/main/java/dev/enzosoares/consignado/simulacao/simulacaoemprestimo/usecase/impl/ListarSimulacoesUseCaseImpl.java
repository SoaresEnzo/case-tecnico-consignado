package dev.enzosoares.consignado.simulacao.simulacaoemprestimo.usecase.impl;

import dev.enzosoares.consignado.simulacao.simulacaoemprestimo.dataprovider.SimulacaoEmprestimoRepository;
import dev.enzosoares.consignado.simulacao.simulacaoemprestimo.usecase.ListarSimulacoesUseCase;
import dev.enzosoares.consignado.simulacao.simulacaoemprestimo.usecase.dto.ListarSimulacaoEmprestimoConsignadoOutputItem;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.List;
import java.util.Objects;

@Named
public class ListarSimulacoesUseCaseImpl implements ListarSimulacoesUseCase {
    private final SimulacaoEmprestimoRepository simulacaoEmprestimoRepository;

    @Inject
    public ListarSimulacoesUseCaseImpl(
            final SimulacaoEmprestimoRepository simulacaoEmprestimoRepository
    ) {
        this.simulacaoEmprestimoRepository = Objects.requireNonNull(simulacaoEmprestimoRepository);
    }

    @Override
    public List<ListarSimulacaoEmprestimoConsignadoOutputItem> listar() {
        return this.simulacaoEmprestimoRepository
                .findAll()
                .stream()
                .map(ListarSimulacaoEmprestimoConsignadoOutputItem::from)
                .toList();
    }
}
