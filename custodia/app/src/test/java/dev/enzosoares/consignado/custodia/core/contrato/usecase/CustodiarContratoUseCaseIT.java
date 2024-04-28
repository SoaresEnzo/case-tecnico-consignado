package dev.enzosoares.consignado.custodia.core.contrato.usecase;

import dev.enzosoares.consignado.custodia.ApplicationTest;
import dev.enzosoares.consignado.custodia.contrato.Contrato;
import dev.enzosoares.consignado.custodia.contrato.dataprovider.ContratoMySQLRepository;
import dev.enzosoares.consignado.custodia.contrato.dataprovider.ContratoRepository;
import dev.enzosoares.consignado.custodia.contrato.usecase.CustodiarContratoUseCase;
import dev.enzosoares.consignado.custodia.contrato.usecase.dto.CustodiarContratoUseCaseInput;
import dev.enzosoares.consignado.custodia.errors.NotFoundException;
import dev.enzosoares.consignado.custodia.simulacaoemprestimo.SimulacaoEmprestimo;
import dev.enzosoares.consignado.custodia.simulacaoemprestimo.facade.SimulacaoEmprestimoFacade;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@ApplicationTest
public class CustodiarContratoUseCaseIT {
    @Autowired
    private CustodiarContratoUseCase custodiarContratoUseCase;

    @SpyBean
    private ContratoRepository contratoRepository;

    @MockBean
    private SimulacaoEmprestimoFacade simulacaoEmprestimoFacade;

    @Autowired
    private ContratoMySQLRepository contratoMySQLRepository;

    @Test
    public void givenANonExistingSimulacao_whenCallCustodiar_thenShouldThrowException() {
        // given
        Mockito.when(simulacaoEmprestimoFacade.fetchById(Mockito.any())).thenReturn(Optional.empty());
        final var simulacaoId = UUID.randomUUID().toString();

        // when
        final var anException = assertThrows(NotFoundException.class, () -> custodiarContratoUseCase.custodiar(new CustodiarContratoUseCaseInput(simulacaoId)));

        // then
        assertEquals("Simulação não encontrada.", anException.getMessage());
    }

    @Test
    public void givenAnExistingSimulacao_whenCallCustodiar_thenShouldCustodiar() {
        // given
        final var simulacaoId = UUID.randomUUID();
        final var simulacaoEmprestimo = SimulacaoEmprestimo.of(
                simulacaoId.toString(),
                Instant.now(),
                "111.111.111-11",
                new BigDecimal("127.00"),
                new BigDecimal("1270.00"),
                new BigDecimal("0.0247"),
                10,
                new BigDecimal("1000.00")
        );
        Mockito.when(simulacaoEmprestimoFacade.fetchById(simulacaoId)).thenReturn(Optional.of(simulacaoEmprestimo));

        // when
        final var savedContrato = custodiarContratoUseCase.custodiar(new CustodiarContratoUseCaseInput(simulacaoId.toString()));

        // then
        assertFalse(contratoMySQLRepository.findAll().isEmpty());
        assertEquals(simulacaoEmprestimo.getIdSimulacao().toString(), savedContrato.idSimulacao());
        Mockito.verify(contratoRepository).save(Mockito.any());
    }
}
