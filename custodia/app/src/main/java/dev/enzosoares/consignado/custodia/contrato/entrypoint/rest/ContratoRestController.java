package dev.enzosoares.consignado.custodia.contrato.entrypoint.rest;

import dev.enzosoares.consignado.custodia.contrato.entrypoint.rest.requests.CustodiarContratoInput;
import dev.enzosoares.consignado.custodia.contrato.entrypoint.rest.responses.CustodiarContratoResponse;
import dev.enzosoares.consignado.custodia.contrato.usecase.CustodiarContratoUseCase;
import dev.enzosoares.consignado.custodia.contrato.usecase.dto.CustodiarContratoUseCaseInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContratoRestController implements ContratoRestEndpoint {
    private final CustodiarContratoUseCase custodiarContratoUseCase;

    @Autowired
    public ContratoRestController(
            final CustodiarContratoUseCase custodiarContratoUseCase
    ) {
        this.custodiarContratoUseCase = custodiarContratoUseCase;
    }

    @Override
    public ResponseEntity<CustodiarContratoResponse> custodiarContrato(CustodiarContratoInput input) {
        final var useCaseInput = new CustodiarContratoUseCaseInput(input.idSimulacao());
        final var useCaseOutput = this.custodiarContratoUseCase.custodiar(useCaseInput);
        return ResponseEntity
                .created(null)
                .body(CustodiarContratoResponse.from(useCaseOutput));
    }
}
