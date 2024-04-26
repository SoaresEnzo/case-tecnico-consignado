package dev.enzosoares.consignado.simulacao.simulacaoemprestimo.entrypoint.rest.responses;

import dev.enzosoares.consignado.simulacao.simulacaoemprestimo.usecase.dto.ListarSimulacaoEmprestimoConsignadoOutputItem;

import java.math.BigDecimal;
import java.time.Instant;

public record ListarSimulacoesResponseItem(
        String idSimulacao,
        String cpf,
        BigDecimal valorParcela,
        Integer quantidadeParcelas,
        BigDecimal valorTotal,
        Instant dataSimulacao,
        BigDecimal taxaJuros,
        BigDecimal valorSolicitado
) {
    public static ListarSimulacoesResponseItem from(
            ListarSimulacaoEmprestimoConsignadoOutputItem item
    ) {
        return new ListarSimulacoesResponseItem(
                item.idSimulacao(),
                item.cpf(),
                item.valorParcela(),
                item.quantidadeParcelas(),
                item.valorTotal(),
                item.dataSimulacao(),
                item.taxaJuros(),
                item.valorSolicitado()
        );
    }
}
