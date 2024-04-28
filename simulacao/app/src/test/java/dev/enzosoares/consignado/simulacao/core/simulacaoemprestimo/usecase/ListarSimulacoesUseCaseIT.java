package dev.enzosoares.consignado.simulacao.core.simulacaoemprestimo.usecase;

import dev.enzosoares.consignado.simulacao.ApplicationTest;
import dev.enzosoares.consignado.simulacao.cliente.valueobject.CPF;
import dev.enzosoares.consignado.simulacao.cliente.valueobject.Convenio;
import dev.enzosoares.consignado.simulacao.simulacaoemprestimo.SimulacaoEmprestimo;
import dev.enzosoares.consignado.simulacao.simulacaoemprestimo.dataprovider.SimulacaoEmprestimoMapper;
import dev.enzosoares.consignado.simulacao.simulacaoemprestimo.dataprovider.SimulacaoEmprestimoMySQLRepository;
import dev.enzosoares.consignado.simulacao.simulacaoemprestimo.dataprovider.SimulacaoEmprestimoRepository;
import dev.enzosoares.consignado.simulacao.simulacaoemprestimo.usecase.ListarSimulacoesUseCase;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ApplicationTest
public class ListarSimulacoesUseCaseIT {
    @Autowired
    private ListarSimulacoesUseCase listarSimulacoesUseCase;

    @SpyBean
    private SimulacaoEmprestimoRepository simulacaoEmprestimoRepository;

    @Autowired
    private SimulacaoEmprestimoMySQLRepository simulacaoEmprestimoMySQLRepository;

    @Test
    public void givenNoPrepersistedSimulacaoEmprestimo_whenCallListarSimulacoes_thenShouldReturnEmptyList() {
        //given
        simulacaoEmprestimoMySQLRepository.deleteAll();

        // when
        final var actual = listarSimulacoesUseCase.listar();

        // then
        assertTrue(actual.isEmpty());
        Mockito.verify(simulacaoEmprestimoRepository, Mockito.times(1)).findAll();
    }

    @Test
    public void givenPrepersistedSimulacaoEmprestimo_whenCallListarSimulacoes_thenShouldReturnSimulacaoEmprestimo() {
        // given
        final var simulacaoEmprestimo1 = SimulacaoEmprestimo.with(
                CPF.with("111.111.111-11"),
                Convenio.EMPRESA_PRIVADA,
                new BigDecimal("124.70"),
                new BigDecimal("1247.00"),
                new BigDecimal("0.0247"),
                10,
                new BigDecimal("1000.00")
        );

        final var simulacaoEmprestimo2 = SimulacaoEmprestimo.with(
                CPF.with("222.222.222-22"),
                Convenio.EMPRESA_PRIVADA,
                new BigDecimal("124.70"),
                new BigDecimal("1247.00"),
                new BigDecimal("0.0247"),
                10,
                new BigDecimal("1000.00")
        );
        final var listOfSimulacoes = List.of(
                simulacaoEmprestimo1,simulacaoEmprestimo2
        );

        listOfSimulacoes
                .stream()
                .map(SimulacaoEmprestimoMapper::toEntity)
                .forEach(simulacaoEmprestimoMySQLRepository::saveAndFlush);

        // when
        final var actual = listarSimulacoesUseCase.listar();

        // then
        assertFalse(actual.isEmpty());
        assertEquals(listOfSimulacoes.size(), actual.size());
        for (int i = 0; i < listOfSimulacoes.size(); i++) {
            assertEquals(listOfSimulacoes.get(i).getIdSimulacao().toString(), actual.get(i).idSimulacao());
            assertEquals(listOfSimulacoes.get(i).getCpfCliente().getValue(), actual.get(i).cpf());
            assertEquals(listOfSimulacoes.get(i).getValorParcela(), actual.get(i).valorParcela());
            assertEquals(listOfSimulacoes.get(i).getQuantidadeParcelas(), actual.get(i).quantidadeParcelas());
            assertEquals(listOfSimulacoes.get(i).getValorEmprestimo(), actual.get(i).valorTotal());
            assertEquals(listOfSimulacoes.get(i).getDataSimulacao(), actual.get(i).dataSimulacao());
            assertEquals(listOfSimulacoes.get(i).getTaxaJuros(), actual.get(i).taxaJuros().stripTrailingZeros());
            assertEquals(listOfSimulacoes.get(i).getValorSolicitado(), actual.get(i).valorSolicitado());
        }

        Mockito.verify(simulacaoEmprestimoRepository, Mockito.times(1)).findAll();
    }
}
