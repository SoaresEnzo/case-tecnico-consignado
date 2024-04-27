package dev.enzosoares.consignado.custodia.contrato.usecase.dto;

import dev.enzosoares.consignado.custodia.contrato.Contrato;

import java.time.Instant;

public record ListarContratoOutputItem(
        String idContrato,
        String idSimulacao,
        Instant dataContratacao
) {
    public static ListarContratoOutputItem from(Contrato contrato) {
        return new ListarContratoOutputItem(contrato.getIdContrato(), contrato.getIdSimulacao(), contrato.getDataContratacao());
    }
}