package dev.enzosoares.consignado.custodia.contrato.entrypoint.rest.responses;

import dev.enzosoares.consignado.custodia.contrato.usecase.dto.CustodiarContratoUseCaseOutput;

import java.time.Instant;

public record CustodiarContratoResponse(
        String idContrato,
        String idSimulacao,
        Instant dataContratacao
) {
    public static CustodiarContratoResponse from(CustodiarContratoUseCaseOutput useCaseOutput) {
        return new CustodiarContratoResponse(useCaseOutput.idContrato(), useCaseOutput.idSimulacao(), useCaseOutput.dataContratacao());
    }
}
