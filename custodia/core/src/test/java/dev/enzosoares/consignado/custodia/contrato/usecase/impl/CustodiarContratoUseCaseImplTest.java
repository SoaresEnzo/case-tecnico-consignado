package dev.enzosoares.consignado.custodia.contrato.usecase.impl;

import dev.enzosoares.consignado.custodia.contrato.Contrato;
import dev.enzosoares.consignado.custodia.contrato.dataprovider.ContratoRepository;
import dev.enzosoares.consignado.custodia.contrato.usecase.dto.CustodiarContratoUseCaseInput;
import dev.enzosoares.consignado.custodia.errors.NotFoundException;
import dev.enzosoares.consignado.custodia.simulacaoemprestimo.SimulacaoEmprestimo;
import dev.enzosoares.consignado.custodia.simulacaoemprestimo.facade.SimulacaoEmprestimoFacade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CustodiarContratoUseCaseImplTest {

    @InjectMocks
    private CustodiarContratoUseCaseImpl custodiarContratoUseCase;

    @Mock
    private ContratoRepository contratoRepository;
    @Mock
    private SimulacaoEmprestimoFacade simulacaoEmprestimoFacade;

    @BeforeEach
    void cleanUp() {
        Mockito.reset(contratoRepository);
        Mockito.reset(simulacaoEmprestimoFacade);
    }

    @Test
    void givenAValidSimulacao_whenCallsCustodiarContrato_thenSaveContrato() {
        // given
        final var simulacao = SimulacaoEmprestimo.of(
                UUID.randomUUID().toString(),
                Instant.now(),
                "111.111.111-11",
                BigDecimal.valueOf(127.00),
                BigDecimal.valueOf(1270.00),
                BigDecimal.valueOf(0.027),
                10,
                BigDecimal.valueOf(1000));

        Mockito.when(
                        simulacaoEmprestimoFacade
                                .fetchById(simulacao.getIdSimulacao()))
                .thenReturn(Optional.of(simulacao));
        final var anInput = new CustodiarContratoUseCaseInput(simulacao.getIdSimulacao().toString());

        // when
        final var result = custodiarContratoUseCase.custodiar(anInput);

        // then
        assertNotNull(result);
        assertEquals(anInput.idSimulacao(), result.idSimulacao());
        assertNotNull(result.idContrato());
        assertNotNull(result.dataContratacao());
        Mockito.verify(contratoRepository, Mockito.times(1)).save(Mockito.any(Contrato.class));
        Mockito.verify(simulacaoEmprestimoFacade, Mockito.times(1)).fetchById(simulacao.getIdSimulacao());
    }

    @Test
    void givenAnUnexistentSimulacao_whenCallsCustodiar_thenShouldThrowException() {
        // given
        final var anInput = new CustodiarContratoUseCaseInput(UUID.randomUUID().toString());
        Mockito.when(
                        simulacaoEmprestimoFacade
                                .fetchById(UUID.fromString(anInput.idSimulacao())))
                .thenReturn(Optional.empty());

        // when
        assertThrows(NotFoundException.class, () -> custodiarContratoUseCase.custodiar(anInput));

        // then
        Mockito.verify(contratoRepository, Mockito.never()).save(Mockito.any(Contrato.class));
        Mockito.verify(simulacaoEmprestimoFacade, Mockito.times(1)).fetchById(UUID.fromString(anInput.idSimulacao()));
    }

}