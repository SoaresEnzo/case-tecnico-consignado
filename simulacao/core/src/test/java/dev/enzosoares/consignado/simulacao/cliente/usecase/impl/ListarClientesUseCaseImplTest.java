package dev.enzosoares.consignado.simulacao.cliente.usecase.impl;

import dev.enzosoares.consignado.simulacao.cliente.Cliente;
import dev.enzosoares.consignado.simulacao.cliente.dataprovider.ClienteRepository;
import dev.enzosoares.consignado.simulacao.cliente.valueobject.CPF;
import dev.enzosoares.consignado.simulacao.cliente.valueobject.Convenio;
import dev.enzosoares.consignado.simulacao.cliente.valueobject.Segmento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ListarClientesUseCaseImplTest {
    @InjectMocks
    private ListarClientesUseCaseImpl listarClientesUseCaseImpl;

    @Mock
    private ClienteRepository clienteRepository;

    @BeforeEach
    void cleanUp() {
        Mockito.reset(clienteRepository);
    }

    @Test
    void givenExistingClientes_whenCallsListarClientes_thenReturnsListOfClientes() {
        // given
        Mockito.when(clienteRepository.findAll()).thenReturn(List.of(
                Cliente.with(CPF.with("111.111.111-11"), "Michael Jackson", true, Segmento.UNICLASS, Convenio.EMPRESA_PRIVADA),
                Cliente.with(CPF.with("222.222.222-22"), "LeBron James", true, Segmento.UNICLASS, Convenio.EMPRESA_PRIVADA)
        ));

        // when
        final var clientes = listarClientesUseCaseImpl.listar();

        // then
        // verifies all fields
        assertNotNull(clientes);
        assertEquals(2, clientes.size());
        assertEquals("Michael Jackson", clientes.getFirst().nome());
        assertEquals("111.111.111-11", clientes.getFirst().cpf());
        assertEquals(true, clientes.getFirst().correntista());
        assertEquals("UNICLASS", clientes.get(0).segmento());
        assertEquals("EMPRESA_PRIVADA", clientes.get(0).convenio());

        assertEquals("LeBron James", clientes.get(1).nome());
        assertEquals("222.222.222-22", clientes.get(1).cpf());
        assertEquals(true, clientes.get(1).correntista());
        assertEquals("UNICLASS", clientes.get(1).segmento());
        assertEquals("EMPRESA_PRIVADA", clientes.get(1).convenio());


        Mockito.verify(clienteRepository, Mockito.times(1)).findAll();
    }

    @Test
    void givenNoClientes_whenCallsListarClientes_thenReturnsEmptyList() {
        // given
        Mockito.when(clienteRepository.findAll()).thenReturn(List.of());

        // when
        final var clientes = listarClientesUseCaseImpl.listar();

        // then
        assertNotNull(clientes);
        assertTrue(clientes.isEmpty());

        Mockito.verify(clienteRepository, Mockito.times(1)).findAll();
    }

    @Test
    void givenAnyException_whenCallsListarClientes_thenThrowsException() {
        // given
        final var expectedMessage = "Erro ao buscar clientes";
        Mockito.when(clienteRepository.findAll()).thenThrow(new RuntimeException(expectedMessage));

        // when
        final var exception = assertThrows(RuntimeException.class, () -> listarClientesUseCaseImpl.listar());

        // then
        assertEquals(expectedMessage, exception.getMessage());

        Mockito.verify(clienteRepository, Mockito.times(1)).findAll();
    }
}