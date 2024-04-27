package dev.enzosoares.consignado.custodia.contrato.usecase.dto;

import dev.enzosoares.consignado.custodia.errors.BadRequestException;

public record CustodiarContratoUseCaseInput(
        String idSimulacao
) {
    public CustodiarContratoUseCaseInput {
        if (idSimulacao == null || idSimulacao.isBlank()) {
            throw new BadRequestException("Id da simulação não pode ser nulo ou vazio");
        }
    }
}
