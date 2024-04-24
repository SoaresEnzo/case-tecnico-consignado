package dev.enzosoares.consignado.simulacao.cliente.usecase.impl;

import dev.enzosoares.consignado.simulacao.cliente.dataprovider.ClienteRepository;
import dev.enzosoares.consignado.simulacao.cliente.usecase.ListarClientesUseCase;
import dev.enzosoares.consignado.simulacao.cliente.usecase.dto.ListarClientesOutputItem;
import dev.enzosoares.consignado.simulacao.simulacaoemprestimo.dataprovider.SimulacaoEmprestimoRepository;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.List;
import java.util.Objects;

@Named
public class ListarClientesUseCaseImpl implements ListarClientesUseCase {
    private final ClienteRepository clienteRepository;

    @Inject
    public ListarClientesUseCaseImpl(
            final ClienteRepository clienteRepository
    ) {
        this.clienteRepository = Objects.requireNonNull(clienteRepository);
    }

    @Override
    public List<ListarClientesOutputItem> listar() {
        return this.clienteRepository
                .findAll()
                .stream()
                .map(ListarClientesOutputItem::from)
                .toList();
    }
}
