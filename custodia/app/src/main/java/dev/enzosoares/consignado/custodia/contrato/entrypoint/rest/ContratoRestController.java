package dev.enzosoares.consignado.custodia.contrato.entrypoint.rest;

import dev.enzosoares.consignado.custodia.contrato.entrypoint.rest.requests.CustodiarContratoInput;
import dev.enzosoares.consignado.custodia.contrato.entrypoint.rest.responses.CustodiarContratoResponse;
import dev.enzosoares.consignado.custodia.contrato.entrypoint.rest.responses.ListarContratoResponseItem;
import dev.enzosoares.consignado.custodia.contrato.usecase.CustodiarContratoUseCase;
import dev.enzosoares.consignado.custodia.contrato.usecase.ListarContratosUseCase;
import dev.enzosoares.consignado.custodia.contrato.usecase.dto.CustodiarContratoUseCaseInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
public class ContratoRestController implements ContratoRestEndpoint {
    private final CustodiarContratoUseCase custodiarContratoUseCase;
    private final ListarContratosUseCase listarContratosUseCase;
    @Autowired
    public ContratoRestController(
            final CustodiarContratoUseCase custodiarContratoUseCase,
            final ListarContratosUseCase listarContratosUseCase
    ) {
        this.custodiarContratoUseCase = Objects.requireNonNull(custodiarContratoUseCase);
        this.listarContratosUseCase = Objects.requireNonNull(listarContratosUseCase);
    }

    @Override
    public ResponseEntity<CustodiarContratoResponse> custodiarContrato(CustodiarContratoInput input) {
        final var useCaseInput = new CustodiarContratoUseCaseInput(input.idSimulacao());
        final var useCaseOutput = this.custodiarContratoUseCase.custodiar(useCaseInput);
        return ResponseEntity
                .created(null)
                .body(CustodiarContratoResponse.from(useCaseOutput));
    }

    @Override
    public ResponseEntity<List<ListarContratoResponseItem>> listarContratos() {
        final var useCaseOutput = this.listarContratosUseCase.listar();
        return ResponseEntity.ok(
                useCaseOutput.stream()
                        .map(ListarContratoResponseItem::from)
                        .toList()
        );
    }
}
