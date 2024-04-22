package dev.enzosoares.consignado.simulacao.cliente.dataprovider;

import dev.enzosoares.consignado.simulacao.cliente.Cliente;
import dev.enzosoares.consignado.simulacao.cliente.valueobject.CPF;

import java.util.Optional;

public interface ClienteRepository {

    Optional<Cliente> findByCpf(CPF cpf);
}
