package dev.enzosoares.consignado.simulacao.core.cliente.usecase;


import dev.enzosoares.consignado.simulacao.ApplicationTest;
import dev.enzosoares.consignado.simulacao.cliente.Cliente;
import dev.enzosoares.consignado.simulacao.cliente.dataprovider.ClienteMapper;
import dev.enzosoares.consignado.simulacao.cliente.dataprovider.ClienteMySQLRepository;
import dev.enzosoares.consignado.simulacao.cliente.dataprovider.ClienteRepository;
import dev.enzosoares.consignado.simulacao.cliente.usecase.ListarClientesUseCase;
import dev.enzosoares.consignado.simulacao.cliente.valueobject.CPF;
import dev.enzosoares.consignado.simulacao.cliente.valueobject.Convenio;
import dev.enzosoares.consignado.simulacao.cliente.valueobject.Segmento;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ApplicationTest
public class ListarClientesUseCaseTestIT {

    @Autowired
    private ListarClientesUseCase listarClientesUseCase;

    @SpyBean
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteMySQLRepository clienteMySQLRepository;

    @Test
    public void givenNoPrepersistedCliente_whenCallListarClientes_thenShouldReturnEmpty() {
        // when
        final var actual = listarClientesUseCase.listar();

        // then
        assertTrue(actual.isEmpty());
    }

    @Test
    public void givenPrepersistedClientes_whenCallListarClientes_thenShouldReturnAllClientes() {
        // given
        final var cliente1 = Cliente.with(
                CPF.with("111.111.111-11"),
                "Michael Jordan",
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
        final var cliente3 = Cliente.with(
                CPF.with("333.333.333-33"),
                "Kobe Bryant",
                true,
                Segmento.UNICLASS,
                Convenio.EMPRESA_PRIVADA
        );
        final var aListOfClientes = List.of(cliente1, cliente2, cliente3);

        aListOfClientes.stream()
                .map(ClienteMapper::toEntity)
                .forEach(clienteMySQLRepository::saveAndFlush);

        // when
        final var result = listarClientesUseCase.listar();

        // then
        assertEquals(aListOfClientes.size(), result.size());
        for (int i = 0; i < aListOfClientes.size(); i++) {
            assertEquals(aListOfClientes.get(i).getCpf().getValue(), result.get(i).cpf());
            assertEquals(aListOfClientes.get(i).getNome(), result.get(i).nome());
            assertEquals(aListOfClientes.get(i).isCorrentista(), result.get(i).correntista());
            assertEquals(aListOfClientes.get(i).getSegmento().name(), result.get(i).segmento());
            assertEquals(aListOfClientes.get(i).getConvenio().toString(), result.get(i).convenio());
        }

        Mockito.verify(clienteRepository, Mockito.times(1)).findAll();
    }


}
