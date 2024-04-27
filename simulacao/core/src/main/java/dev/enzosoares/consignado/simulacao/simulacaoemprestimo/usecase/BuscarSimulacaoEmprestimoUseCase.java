package dev.enzosoares.consignado.simulacao.simulacaoemprestimo.usecase;

import dev.enzosoares.consignado.simulacao.simulacaoemprestimo.usecase.dto.BuscarSimulacaoEmprestimoOutput;

public interface BuscarSimulacaoEmprestimoUseCase {
    BuscarSimulacaoEmprestimoOutput buscar(String input);
}
