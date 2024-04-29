package dev.enzosoares.consignado.custodia.contrato.entrypoint.rest.responses;

import dev.enzosoares.consignado.custodia.contrato.usecase.dto.CustodiarContratoUseCaseOutput;
import dev.enzosoares.consignado.custodia.contrato.usecase.dto.ListarContratoOutputItem;

import java.time.Instant;

public record ListarContratoResponseItem (
        String idContrato,
        String idSimulacao,
        Instant dataContratacao
) {
    public static ListarContratoResponseItem from(ListarContratoOutputItem useCaseOutput) {
        return new ListarContratoResponseItem(useCaseOutput.idContrato(), useCaseOutput.idSimulacao(), useCaseOutput.dataContratacao());
    }
}
