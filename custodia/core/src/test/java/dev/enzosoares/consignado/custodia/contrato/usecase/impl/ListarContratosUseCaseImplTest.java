package dev.enzosoares.consignado.custodia.contrato.usecase.impl;

import dev.enzosoares.consignado.custodia.contrato.Contrato;
import dev.enzosoares.consignado.custodia.contrato.dataprovider.ContratoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class ListarContratosUseCaseImplTest {
    @InjectMocks
    private ListarContratosUseCaseImpl listarSimulacoesUseCaseImpl;

    @Mock
    private ContratoRepository contratoRepository;

    @BeforeEach
    void cleanUp() {
        Mockito.reset(contratoRepository);
    }

    @Test
    void givenContratosExisting_whenListarContratos_thenListContratos() {
        // given
        List<Contrato> contratos = List.of(
                Contrato.with(UUID.randomUUID().toString()),
                Contrato.with(UUID.randomUUID().toString()),
                Contrato.with(UUID.randomUUID().toString())
        );

        Mockito.when(contratoRepository.findAll()).thenReturn(contratos);

        // when
        final var result = listarSimulacoesUseCaseImpl.listar();

        // then

        for (int i = 0; i < contratos.size(); i++) {
            assertEquals(contratos.get(i).getIdContrato(), result.get(i).idContrato());
            assertEquals(contratos.get(i).getDataContratacao(), result.get(i).dataContratacao());
            assertEquals(contratos.get(i).getIdSimulacao(), result.get(i).idSimulacao());
        }
        Mockito.verify(contratoRepository, Mockito.times(1)).findAll();
    }

    @Test
    void givenContratosNotExisting_whenListarContratos_thenListContratos() {
        // given
        List<Contrato> contratos = List.of();

        Mockito.when(contratoRepository.findAll()).thenReturn(contratos);

        // when
        final var result = listarSimulacoesUseCaseImpl.listar();

        // then
        assertNotNull(result);
        assertEquals(0, result.size());
        Mockito.verify(contratoRepository, Mockito.times(1)).findAll();
    }
}