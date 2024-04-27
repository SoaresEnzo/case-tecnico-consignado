package dev.enzosoares.consignado.custodia.contrato.usecase;

import dev.enzosoares.consignado.custodia.contrato.usecase.dto.CustodiarContratoUseCaseInput;
import dev.enzosoares.consignado.custodia.contrato.usecase.dto.CustodiarContratoUseCaseOutput;

public interface CustodiarContratoUseCase {
    CustodiarContratoUseCaseOutput custodiar(CustodiarContratoUseCaseInput input);
}
