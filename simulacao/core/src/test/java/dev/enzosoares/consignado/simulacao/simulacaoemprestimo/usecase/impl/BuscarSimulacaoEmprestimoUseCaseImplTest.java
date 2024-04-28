package dev.enzosoares.consignado.simulacao.simulacaoemprestimo.usecase.impl;

import dev.enzosoares.consignado.simulacao.cliente.valueobject.CPF;
import dev.enzosoares.consignado.simulacao.cliente.valueobject.Convenio;
import dev.enzosoares.consignado.simulacao.errors.NotFoundException;
import dev.enzosoares.consignado.simulacao.simulacaoemprestimo.SimulacaoEmprestimo;
import dev.enzosoares.consignado.simulacao.simulacaoemprestimo.dataprovider.SimulacaoEmprestimoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class BuscarSimulacaoEmprestimoUseCaseImplTest {
    @InjectMocks
    private BuscarSimulacaoEmprestimoUseCaseImpl buscarSimulacaoEmprestimoUseCase;

    @Mock
    private SimulacaoEmprestimoRepository simulacaoEmprestimoRepository;

    @BeforeEach
    void cleanUp() {
        Mockito.reset(simulacaoEmprestimoRepository);
    }

    @Test
    void givenAnExistingSimulacao_whenBuscarSimulacao_shouldReturnSimulacao() {
        // given
        final var simulacao = SimulacaoEmprestimo.with(
                CPF.with("111.111.111-11"),
                Convenio.EMPRESA_PRIVADA,
                new BigDecimal("124.70"),
                new BigDecimal("1247.00"),
                new BigDecimal("0.0247"),
                10,
                new BigDecimal("1000.00")
        );

        Mockito.when(simulacaoEmprestimoRepository.findById(simulacao.getIdSimulacao().toString()))
                .thenReturn(java.util.Optional.of(simulacao));

        // when
        final var result = buscarSimulacaoEmprestimoUseCase.buscar(simulacao.getIdSimulacao().toString());

        // then
        assertEquals(simulacao.getIdSimulacao().toString(), result.idSimulacao());
        assertEquals(simulacao.getCpfCliente().getValue(), result.cpf());
        assertEquals(simulacao.getValorParcela(), result.valorParcela());
        assertEquals(simulacao.getQuantidadeParcelas(), result.quantidadeParcelas());
        assertEquals(simulacao.getValorEmprestimo(), result.valorTotal());
        assertEquals(simulacao.getDataSimulacao(), result.dataSimulacao());
        assertEquals(simulacao.getTaxaJuros(), result.taxaJuros());
        assertEquals(simulacao.getValorSolicitado(), result.valorSolicitado());
    }

    @Test
    void givenANonExistingSimulacao_whenBuscarSimulacao_shouldThrowException() {
        // given
        final var expectedMessage = "Simulação não encontrada.";
        final var idSimulacao = UUID.randomUUID().toString();

        Mockito.when(simulacaoEmprestimoRepository.findById(idSimulacao))
                .thenReturn(Optional.empty());

        // when
        final var anException = assertThrows(NotFoundException.class, () -> buscarSimulacaoEmprestimoUseCase.buscar(idSimulacao));

        // then
        assertEquals(expectedMessage, anException.getMessage());
    }

    @Test
    void givenARepositoryException_whenBuscarSimulacao_shouldThrowException() {
        // given
        final var expectedMessage = "Erro ao buscar simulação.";
        final var idSimulacao = UUID.randomUUID().toString();

        Mockito.when(simulacaoEmprestimoRepository.findById(idSimulacao))
                .thenThrow(new RuntimeException(expectedMessage));

        // when
        final var anException = assertThrows(RuntimeException.class, () -> buscarSimulacaoEmprestimoUseCase.buscar(idSimulacao));

        // then
        assertEquals(expectedMessage, anException.getMessage());
    }
}