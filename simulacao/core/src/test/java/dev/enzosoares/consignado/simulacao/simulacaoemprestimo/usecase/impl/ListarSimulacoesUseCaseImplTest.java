package dev.enzosoares.consignado.simulacao.simulacaoemprestimo.usecase.impl;

import dev.enzosoares.consignado.simulacao.cliente.valueobject.CPF;
import dev.enzosoares.consignado.simulacao.cliente.valueobject.Convenio;
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
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ListarSimulacoesUseCaseImplTest {
    @InjectMocks
    private ListarSimulacoesUseCaseImpl listarSimulacoesUseCaseImpl;

    @Mock
    private SimulacaoEmprestimoRepository simulacaoEmprestimoRepository;

    @BeforeEach
    void cleanUp() {
        Mockito.reset(simulacaoEmprestimoRepository);
    }

    @Test
    void givenSimulacoesExisting_whenListarSimulacoes_thenListSimulacoes() {
        // given
        List<SimulacaoEmprestimo> simulacoes = List.of(
                SimulacaoEmprestimo.with(
                        CPF.with("111.111.111-11"),
                        Convenio.EMPRESA_PRIVADA,
                        new BigDecimal("124.70"),
                        new BigDecimal("1247.00"),
                        new BigDecimal("0.0247"),
                        10,
                        new BigDecimal("1000.00")
                ),
                SimulacaoEmprestimo.with(
                        CPF.with("222.222.222-22"),
                        Convenio.EMPRESA_PRIVADA,
                        new BigDecimal("124.70"),
                        new BigDecimal("1247.00"),
                        new BigDecimal("0.0247"),
                        10,
                        new BigDecimal("1000.00")
                )
        );
        Mockito.when(simulacaoEmprestimoRepository.findAll()).thenReturn(simulacoes);

        // when
        final var result = listarSimulacoesUseCaseImpl.listar();

        // then
        assertNotNull(result);
        assertEquals(2, result.size());
        Mockito.verify(simulacaoEmprestimoRepository, Mockito.times(1)).findAll();

        final var simulacao1 = result.getFirst();
        assertEquals(simulacoes.getFirst().getIdSimulacao().toString(), simulacao1.idSimulacao());
        assertEquals(simulacoes.getFirst().getCpfCliente().getValue(), simulacao1.cpf());
        assertEquals(simulacoes.getFirst().getValorParcela(), simulacao1.valorParcela());
        assertEquals(simulacoes.getFirst().getValorEmprestimo(), simulacao1.valorTotal());
        assertEquals(simulacoes.getFirst().getTaxaJuros(), simulacao1.taxaJuros());
        assertEquals(simulacoes.getFirst().getQuantidadeParcelas(), simulacao1.quantidadeParcelas());
        assertEquals(simulacoes.get(0).getValorSolicitado(), simulacao1.valorSolicitado());

        final var simulacao2 = result.get(1);
        assertEquals(simulacoes.get(1).getIdSimulacao().toString(), simulacao2.idSimulacao());
        assertEquals(simulacoes.get(1).getCpfCliente().getValue(), simulacao2.cpf());
        assertEquals(simulacoes.get(1).getValorParcela(), simulacao2.valorParcela());
        assertEquals(simulacoes.get(1).getValorEmprestimo(), simulacao2.valorTotal());
        assertEquals(simulacoes.get(1).getTaxaJuros(), simulacao2.taxaJuros());
        assertEquals(simulacoes.get(1).getQuantidadeParcelas(), simulacao2.quantidadeParcelas());
        assertEquals(simulacoes.get(1).getValorSolicitado(), simulacao2.valorSolicitado());
    }

    @Test
    void givenSimulacoesNotExisting_whenListarSimulacoes_thenListSimulacoes() {
        // given
        Mockito.when(simulacaoEmprestimoRepository.findAll()).thenReturn(List.of());

        // when
        final var result = listarSimulacoesUseCaseImpl.listar();

        // then
        assertNotNull(result);
        assertTrue(result.isEmpty());
        Mockito.verify(simulacaoEmprestimoRepository, Mockito.times(1)).findAll();
    }

    @Test
    void givenAnyException_whenListarSimulacoes_thenThrowException() {
        final var expectedMessage = "Erro ao buscar simulações";
        // given
        Mockito.when(simulacaoEmprestimoRepository.findAll()).thenThrow(new RuntimeException(expectedMessage));

        // when
        final var exception = assertThrows(RuntimeException.class, () -> listarSimulacoesUseCaseImpl.listar());

        // then
        assertEquals(expectedMessage, exception.getMessage());
        Mockito.verify(simulacaoEmprestimoRepository, Mockito.times(1)).findAll();
    }

}