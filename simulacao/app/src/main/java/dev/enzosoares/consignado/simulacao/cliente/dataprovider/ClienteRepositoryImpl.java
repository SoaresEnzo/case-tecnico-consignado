package dev.enzosoares.consignado.simulacao.cliente.dataprovider;

import dev.enzosoares.consignado.simulacao.cliente.Cliente;
import dev.enzosoares.consignado.simulacao.cliente.valueobject.CPF;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ClienteRepositoryImpl implements ClienteRepository {

    private final ClienteMySQLRepository clienteMySQLRepository;

    public ClienteRepositoryImpl(ClienteMySQLRepository clienteMySQLRepository) {
        this.clienteMySQLRepository = clienteMySQLRepository;
    }

    @Override
    public Optional<Cliente> findByCpf(CPF cpf) {
        return this.clienteMySQLRepository.findById(cpf.getValue())
                .map(ClienteMapper::toDomain);
    }

    @Override
    public List<Cliente> findAll() {
        return this.clienteMySQLRepository.findAll()
                .stream()
                .map(ClienteMapper::toDomain)
                .toList();
    }
}
