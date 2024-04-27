package dev.enzosoares.consignado.custodia.simulacaoemprestimo.facade.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dev.enzosoares.consignado.custodia.simulacaoemprestimo.SimulacaoEmprestimo;

import java.math.BigDecimal;
import java.time.Instant;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GetSimulacaoEmprestimoResponseBody(
        String idSimulacao,
        String cpf,
        BigDecimal valorParcela,
        Integer quantidadeParcelas,
        BigDecimal valorTotal,
        Instant dataSimulacao,
        BigDecimal taxaJuros,
        BigDecimal valorSolicitado
) {

    public static SimulacaoEmprestimo toDomain(GetSimulacaoEmprestimoResponseBody responseBody) {
        return SimulacaoEmprestimo.of(
                responseBody.idSimulacao(),
                responseBody.dataSimulacao(),
                responseBody.cpf(),
                responseBody.valorParcela(),
                responseBody.valorSolicitado(),
                responseBody.taxaJuros(),
                responseBody.quantidadeParcelas(),
                responseBody.valorTotal()
        );
    }

}
