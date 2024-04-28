package dev.enzosoares.consignado.simulacao.cliente.dataprovider;

import dev.enzosoares.consignado.simulacao.ApplicationTest;
import dev.enzosoares.consignado.simulacao.cliente.Cliente;
import dev.enzosoares.consignado.simulacao.cliente.valueobject.CPF;
import dev.enzosoares.consignado.simulacao.cliente.valueobject.Convenio;
import dev.enzosoares.consignado.simulacao.cliente.valueobject.Segmento;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ApplicationTest
class ClienteRepositoryImplTest {
    @Autowired
    private ClienteRepositoryImpl clienteRepository;

    @Autowired
    private ClienteMySQLRepository clienteMySQLRepository;

    @Test
    public void givenAPrepersistedCliente_whenCallFindByCPF_thenShouldReturnCliente() {
        //given
        final var expectedCpf = "222.222.222-22";
        final var cliente = Cliente.with(
                CPF.with(expectedCpf),
                "LeBron James",
                true,
                Segmento.UNICLASS,
                Convenio.EMPRESA_PRIVADA
        );
        final var clienteEntity = ClienteMapper.toEntity(cliente);

        clienteMySQLRepository.save(clienteEntity);

        // when
        clienteRepository.findByCpf(CPF.with(expectedCpf));

        // then

        final var actualEntity = clienteMySQLRepository.findById(expectedCpf);
        assertTrue(actualEntity.isPresent());

        final var actual = ClienteMapper.toDomain(actualEntity.get());
        assertEquals(cliente.getCpf().getValue(), actual.getCpf().getValue());
        assertEquals(cliente.getNome(), actual.getNome());
        assertEquals(cliente.isCorrentista(), actual.isCorrentista());
        assertEquals(cliente.getSegmento().getKey(), actual.getSegmento().getKey());
        assertEquals(cliente.getConvenio().getKey(), actual.getConvenio().getKey());
    }

    @Test
    public void givenNoPrepersistedCliente_whenCallFindByCPF_thenShouldReturnEmpty() {
        //given
        final var expectedCpf = "333.333.333-33";

        // when
        clienteRepository.findByCpf(CPF.with(expectedCpf));

        // then
        final var actualEntity = clienteMySQLRepository.findById(expectedCpf);
        assertTrue(actualEntity.isEmpty());
    }

    @Test
    public void givenAListOfPrepersistedClientes_whenCallFindAll_thenShouldReturnAllClientes() {
        //given
        final var cliente1 = Cliente.with(
                CPF.with("111.111.111-11"),
                "Michael Jackson",
                true,
                Segmento.UNICLASS,
                Convenio.EMPRESA_PRIVADA
        );
        final var cliente2 = Cliente.with(
                CPF.with("222.222.222-22"),
                "LeBron James",
                true,
                Segmento.UNICLASS,
                Convenio.EMPRESA_PRIVADA
        );
        final var aListOfClientes = List.of(cliente1, cliente2);
        aListOfClientes
                .stream()
                .map(ClienteMapper::toEntity)
                .forEach(clienteMySQLRepository::save);

        // when
        clienteRepository.findAll();

        // then
        final var actualEntities = clienteMySQLRepository.findAll();
        assertEquals(2, actualEntities.size());

        for (int i = 0; i < aListOfClientes.size(); i++) {
            final var expected = aListOfClientes.get(i);
            final var actual = ClienteMapper.toDomain(actualEntities.get(i));
            assertEquals(expected.getCpf().getValue(), actual.getCpf().getValue());
            assertEquals(expected.getNome(), actual.getNome());
            assertEquals(expected.isCorrentista(), actual.isCorrentista());
            assertEquals(expected.getSegmento().getKey(), actual.getSegmento().getKey());
            assertEquals(expected.getConvenio().getKey(), actual.getConvenio().getKey());
        }
    }

    @Test
    public void givenNoPrepersistedClientes_whenCallFindAll_thenShouldReturnEmptyList() {
        // when
        clienteRepository.findAll();

        // then
        final var actualEntities = clienteMySQLRepository.findAll();
        assertTrue(actualEntities.isEmpty());
    }

}