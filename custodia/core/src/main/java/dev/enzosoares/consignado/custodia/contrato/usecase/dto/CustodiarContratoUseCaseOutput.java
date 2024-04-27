package dev.enzosoares.consignado.custodia.contrato.usecase.dto;

import dev.enzosoares.consignado.custodia.contrato.Contrato;

import java.time.Instant;

public record CustodiarContratoUseCaseOutput(
        String idContrato,
        String idSimulacao,
        Instant dataContratacao
) {
    public static CustodiarContratoUseCaseOutput from(
            final Contrato contrato
    ) {
        return new CustodiarContratoUseCaseOutput(
                contrato.getIdContrato(),
                contrato.getIdSimulacao(),
                contrato.getDataContratacao()
        );
    }
}
