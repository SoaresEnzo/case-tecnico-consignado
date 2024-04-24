package dev.enzosoares.consignado.simulacao.cliente.usecase;

import dev.enzosoares.consignado.simulacao.cliente.usecase.dto.ListarClientesOutputItem;

import java.util.List;

public interface ListarClientesUseCase {
    List<ListarClientesOutputItem> listar();
}
