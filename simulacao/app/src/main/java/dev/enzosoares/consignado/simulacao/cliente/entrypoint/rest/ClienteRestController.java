package dev.enzosoares.consignado.simulacao.cliente.entrypoint.rest;

import dev.enzosoares.consignado.simulacao.cliente.entrypoint.rest.responses.ListarClientesResponseItem;
import dev.enzosoares.consignado.simulacao.cliente.usecase.ListarClientesUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
public class ClienteRestController implements ClienteRestEntrypoint {
    private final ListarClientesUseCase listarClientesUseCase;

    @Autowired
    public ClienteRestController(
            final ListarClientesUseCase listarClientesUseCase
    ) {
        this.listarClientesUseCase = Objects.requireNonNull(listarClientesUseCase);
    }

    @Override
    public ResponseEntity<List<ListarClientesResponseItem>> listarClientes() {
        return ResponseEntity.ok(
                listarClientesUseCase
                        .listar()
                        .stream()
                        .map(ListarClientesResponseItem::from)
                        .toList()
        );
    }
}
