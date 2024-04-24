package dev.enzosoares.consignado.simulacao.simulacaoemprestimo.usecase;

import dev.enzosoares.consignado.simulacao.simulacaoemprestimo.usecase.dto.ListarSimulacaoEmprestimoConsignadoOutputItem;

import java.util.List;

public interface ListarSimulacoesUseCase {
    List<ListarSimulacaoEmprestimoConsignadoOutputItem> listar();
}
