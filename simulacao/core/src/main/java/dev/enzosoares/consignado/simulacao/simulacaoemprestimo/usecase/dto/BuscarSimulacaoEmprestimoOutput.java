package dev.enzosoares.consignado.simulacao.simulacaoemprestimo.usecase.dto;

import dev.enzosoares.consignado.simulacao.simulacaoemprestimo.SimulacaoEmprestimo;

import java.math.BigDecimal;
import java.time.Instant;

public record BuscarSimulacaoEmprestimoOutput(
        String idSimulacao,
        String cpf,
        BigDecimal valorParcela,
        Integer quantidadeParcelas,
        BigDecimal valorTotal,
        Instant dataSimulacao,
        BigDecimal taxaJuros,
        BigDecimal valorSolicitado
) {
    public static BuscarSimulacaoEmprestimoOutput from(final SimulacaoEmprestimo simulacaoEmprestimo) {
        return new BuscarSimulacaoEmprestimoOutput(
                simulacaoEmprestimo.getIdSimulacao().toString(),
                simulacaoEmprestimo.getCpfCliente().getValue(),
                simulacaoEmprestimo.getValorParcela(),
                simulacaoEmprestimo.getQuantidadeParcelas(),
                simulacaoEmprestimo.getValorEmprestimo(),
                simulacaoEmprestimo.getDataSimulacao(),
                simulacaoEmprestimo.getTaxaJuros(),
                simulacaoEmprestimo.getValorSolicitado()
        );
    }
}
