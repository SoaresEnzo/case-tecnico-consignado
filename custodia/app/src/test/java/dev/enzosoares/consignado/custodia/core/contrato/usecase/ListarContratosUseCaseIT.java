package dev.enzosoares.consignado.custodia.core.contrato.usecase;

import dev.enzosoares.consignado.custodia.ApplicationTest;
import dev.enzosoares.consignado.custodia.contrato.Contrato;
import dev.enzosoares.consignado.custodia.contrato.dataprovider.ContratoMapper;
import dev.enzosoares.consignado.custodia.contrato.dataprovider.ContratoMySQLRepository;
import dev.enzosoares.consignado.custodia.contrato.dataprovider.ContratoRepository;
import dev.enzosoares.consignado.custodia.contrato.usecase.CustodiarContratoUseCase;
import dev.enzosoares.consignado.custodia.contrato.usecase.ListarContratosUseCase;
import dev.enzosoares.consignado.custodia.simulacaoemprestimo.facade.SimulacaoEmprestimoFacade;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.UUID;

@ApplicationTest
public class ListarContratosUseCaseIT {

    @Autowired
    private ListarContratosUseCase listarContratosUseCase;

    @SpyBean
    private ContratoRepository contratoRepository;

    @Autowired
    private ContratoMySQLRepository contratoMySQLRepository;

    @Test
    public void givenAListOfPrepersistedContratos_whenListarContratos_thenShouldReturnAllContratos() {
        // given
        final var contrato1 = Contrato.with(UUID.randomUUID().toString());
        final var contrato2 = Contrato.with(UUID.randomUUID().toString());
        final var contrato3 = Contrato.with(UUID.randomUUID().toString());
        final var aListOfContratos = List.of(contrato1, contrato2, contrato3);
        aListOfContratos.stream()
                .map(ContratoMapper::toEntity)
                .forEach(contratoMySQLRepository::saveAndFlush);

        // when
        final var result = listarContratosUseCase.listar();
        // then
        assertEquals(aListOfContratos.size(), result.size());
        for (int i = 0; i < aListOfContratos.size(); i++) {
            assertEquals(aListOfContratos.get(i).getIdContrato(), result.get(i).idContrato());
            assertEquals(aListOfContratos.get(i).getIdSimulacao(), result.get(i).idSimulacao());
            assertEquals(aListOfContratos.get(i).getDataContratacao(), result.get(i).dataContratacao());
        }
        Mockito.verify(contratoRepository, Mockito.times(1)).findAll();
    }

    @Test
    public void givenNoPrepersistedContratos_whenListarContratos_thenShouldReturnEmptyList() {
        // given
        contratoMySQLRepository.deleteAll();

        // when
        final var result = listarContratosUseCase.listar();

        // then
        assertTrue(result.isEmpty());
        Mockito.verify(contratoRepository, Mockito.times(1)).findAll();
    }
}
