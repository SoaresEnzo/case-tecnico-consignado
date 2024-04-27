package dev.enzosoares.consignado.custodia.contrato.usecase;

import dev.enzosoares.consignado.custodia.contrato.usecase.dto.ListarContratoOutputItem;

import java.util.List;

public interface ListarContratosUseCase {
    List<ListarContratoOutputItem> listar();
}
