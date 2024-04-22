package dev.enzosoares.consignado.simulacao.simulacaoemprestimo.usecase.dto;

import java.math.BigDecimal;
import java.time.Instant;

public record SimularEmprestimoConsignadoOutput(
        String idSimulacao,
        Instant dataSimulacao,
        String cpf,
        BigDecimal valorParcela,
        BigDecimal valorTotal,
        Integer quantidadeParcelas,
        BigDecimal taxaJuros
) {
}
