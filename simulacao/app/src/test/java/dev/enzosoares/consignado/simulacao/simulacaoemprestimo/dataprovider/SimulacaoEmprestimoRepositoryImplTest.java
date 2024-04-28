package dev.enzosoares.consignado.simulacao.simulacaoemprestimo.dataprovider;

import dev.enzosoares.consignado.simulacao.ApplicationTest;
import dev.enzosoares.consignado.simulacao.cliente.valueobject.CPF;
import dev.enzosoares.consignado.simulacao.cliente.valueobject.Convenio;
import dev.enzosoares.consignado.simulacao.simulacaoemprestimo.SimulacaoEmprestimo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ApplicationTest
class SimulacaoEmprestimoRepositoryImplTest {

    @Autowired
    private SimulacaoEmprestimoRepositoryImpl simulacaoEmprestimoRepository;

    @Autowired
    private SimulacaoEmprestimoMySQLRepository simulacaoEmprestimoMySQLRepository;

    @Test
    public void givenAValidSimulacao_whenCallSave_thenShouldSave() {
        //given
        final var simulacao = SimulacaoEmprestimo.with(
                CPF.with("111.111.111-11"),
                Convenio.EMPRESA_PRIVADA,
                new BigDecimal("124.70"),
                new BigDecimal("1247.00"),
                new BigDecimal("0.0247"),
                10,
                new BigDecimal("1000.00")
        );

        simulacaoEmprestimoRepository.save(simulacao);

        final var simulacaoSaved = simulacaoEmprestimoMySQLRepository.findById(simulacao.getIdSimulacao().toString());
        assertTrue(simulacaoSaved.isPresent());

        final var actualEntity = simulacaoSaved.get();
        assertEquals(simulacao.getIdSimulacao().toString(), actualEntity.getId());
        assertEquals(simulacao.getDataSimulacao(), actualEntity.getDataSimulacao());
        assertEquals(simulacao.getCpfCliente().getValue(), actualEntity.getCpf());
        assertEquals(simulacao.getConvenioCliente().getKey(), actualEntity.getConvenio());
        assertEquals(simulacao.getValorSolicitado(), actualEntity.getValorSolicitado());
        assertEquals(simulacao.getTaxaJuros(), actualEntity.getTaxaAplicada().stripTrailingZeros());
        assertEquals(simulacao.getQuantidadeParcelas(), actualEntity.getQuantidadeParcelas());
        assertEquals(simulacao.getValorParcela(), actualEntity.getValorParcela());
        assertEquals(simulacao.getValorEmprestimo(), actualEntity.getValorTotal());
    }

    @Test
    public void givenAPrepersistedSimulacao_whenCallFindById_thenShouldReturnTheDesiredSimulacao() {
        //given
        final var simulacao = SimulacaoEmprestimo.with(
                CPF.with("111.111.111-11"),
                Convenio.EMPRESA_PRIVADA,
                new BigDecimal("124.70"),
                new BigDecimal("1247.00"),
                new BigDecimal("0.0247"),
                10,
                new BigDecimal("1000.00")
        );
        final var simulacaoEntity = SimulacaoEmprestimoMapper.toEntity(simulacao);
        simulacaoEmprestimoMySQLRepository.save(simulacaoEntity);

        //when
        final var savedEntity = simulacaoEmprestimoRepository.findById(simulacao.getIdSimulacao().toString());

        //then
        assertTrue(savedEntity.isPresent());

        final var actualSavedEntity = savedEntity.get();
        assertEquals(simulacao.getIdSimulacao(), actualSavedEntity.getIdSimulacao());
        assertEquals(simulacao.getCpfCliente(), actualSavedEntity.getCpfCliente());
        assertEquals(simulacao.getConvenioCliente(), actualSavedEntity.getConvenioCliente());
        assertEquals(simulacao.getValorSolicitado(), actualSavedEntity.getValorSolicitado());
        assertEquals(simulacao.getTaxaJuros(), actualSavedEntity.getTaxaJuros().stripTrailingZeros());
        assertEquals(simulacao.getQuantidadeParcelas(), actualSavedEntity.getQuantidadeParcelas());
        assertEquals(simulacao.getValorParcela(), actualSavedEntity.getValorParcela());
        assertEquals(simulacao.getValorEmprestimo(), actualSavedEntity.getValorEmprestimo());
    }

    @Test
    public void givenNoPrepersistedSimulacao_whenCallFindById_thenShouldReturnEmpty() {
        //given
        final var id = "123";

        //when
        final var savedEntity = simulacaoEmprestimoRepository.findById(id);

        //then
        assertTrue(savedEntity.isEmpty());
    }

    @Test
    public void givenAListOfPrepersistedSimulacao_whenCallFindAll_shouldReturnAllSimulacoes() {
        //given
        final var simulacao1 = SimulacaoEmprestimo.with(
                CPF.with("111.111.111-11"),
                Convenio.EMPRESA_PRIVADA,
                new BigDecimal("124.70"),
                new BigDecimal("1247.00"),
                new BigDecimal("0.0247"),
                10,
                new BigDecimal("1000.00")
        );
        final var simulacao2 = SimulacaoEmprestimo.with(
                CPF.with("222.222.222-22"),
                Convenio.EMPRESA_PRIVADA,
                new BigDecimal("124.70"),
                new BigDecimal("1247.00"),
                new BigDecimal("0.0247"),
                10,
                new BigDecimal("1000.00")
        );
        final var simulacao3 = SimulacaoEmprestimo.with(
                CPF.with("333.333.333-33"),
                Convenio.EMPRESA_PRIVADA,
                new BigDecimal("124.70"),
                new BigDecimal("1247.00"),
                new BigDecimal("0.0247"),
                10,
                new BigDecimal("1000.00")
        );

        final var aListOfSimulacoes = List.of(simulacao1, simulacao2, simulacao3);

        aListOfSimulacoes.stream().map(SimulacaoEmprestimoMapper::toEntity).forEach(simulacaoEmprestimoMySQLRepository::save);

        //when
        final var savedEntities = simulacaoEmprestimoRepository.findAll();

        //then
        assertEquals(3, savedEntities.size());

        for (int i = 0; i < aListOfSimulacoes.size(); i++) {
            final var expected = aListOfSimulacoes.get(i);
            final var actual = savedEntities.get(i);
            assertEquals(expected.getIdSimulacao(), actual.getIdSimulacao());
            assertEquals(expected.getCpfCliente(), actual.getCpfCliente());
            assertEquals(expected.getConvenioCliente(), actual.getConvenioCliente());
            assertEquals(expected.getValorSolicitado(), actual.getValorSolicitado());
            assertEquals(expected.getTaxaJuros(), actual.getTaxaJuros().stripTrailingZeros());
            assertEquals(expected.getQuantidadeParcelas(), actual.getQuantidadeParcelas());
            assertEquals(expected.getValorParcela(), actual.getValorParcela());
            assertEquals(expected.getValorEmprestimo(), actual.getValorEmprestimo());
        }
    }

    @Test
    public void givenNoPrepersistedSimulacao_whenCallFindAll_shouldReturnEmptyList() {
        //when
        final var savedEntities = simulacaoEmprestimoRepository.findAll();

        //then
        assertTrue(savedEntities.isEmpty());
    }

}