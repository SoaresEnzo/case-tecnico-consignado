package dev.enzosoares.consignado.custodia.contrato.usecase.impl;

import dev.enzosoares.consignado.custodia.contrato.dataprovider.ContratoRepository;
import dev.enzosoares.consignado.custodia.contrato.usecase.ListarContratosUseCase;
import dev.enzosoares.consignado.custodia.contrato.usecase.dto.ListarContratoOutputItem;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.List;
import java.util.Objects;

@Named
public class ListarContratosUseCaseImpl implements ListarContratosUseCase {
    private final ContratoRepository contratoRepository;

    @Inject
    public ListarContratosUseCaseImpl(
            final ContratoRepository contratoRepository
    ) {
        this.contratoRepository = Objects.requireNonNull(contratoRepository);
    }

    @Override
    public List<ListarContratoOutputItem> listar() {
        return this.contratoRepository
                .findAll()
                .stream()
                .map(ListarContratoOutputItem::from)
                .toList();
    }
}
