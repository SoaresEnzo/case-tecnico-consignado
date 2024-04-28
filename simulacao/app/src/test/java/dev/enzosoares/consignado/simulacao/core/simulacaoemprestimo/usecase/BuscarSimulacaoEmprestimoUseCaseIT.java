package dev.enzosoares.consignado.simulacao.core.simulacaoemprestimo.usecase;

import dev.enzosoares.consignado.simulacao.ApplicationTest;
import dev.enzosoares.consignado.simulacao.cliente.dataprovider.ClienteMySQLRepository;
import dev.enzosoares.consignado.simulacao.cliente.dataprovider.ClienteRepository;
import dev.enzosoares.consignado.simulacao.cliente.usecase.ListarClientesUseCase;
import dev.enzosoares.consignado.simulacao.cliente.valueobject.CPF;
import dev.enzosoares.consignado.simulacao.cliente.valueobject.Convenio;
import dev.enzosoares.consignado.simulacao.errors.NotFoundException;
import dev.enzosoares.consignado.simulacao.simulacaoemprestimo.SimulacaoEmprestimo;
import dev.enzosoares.consignado.simulacao.simulacaoemprestimo.dataprovider.SimulacaoEmprestimoMapper;
import dev.enzosoares.consignado.simulacao.simulacaoemprestimo.dataprovider.SimulacaoEmprestimoMySQLRepository;
import dev.enzosoares.consignado.simulacao.simulacaoemprestimo.dataprovider.SimulacaoEmprestimoRepository;
import dev.enzosoares.consignado.simulacao.simulacaoemprestimo.usecase.BuscarSimulacaoEmprestimoUseCase;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;


@ApplicationTest
public class BuscarSimulacaoEmprestimoUseCaseIT {
    @Autowired
    private BuscarSimulacaoEmprestimoUseCase buscarSimulacaoEmprestimoUseCase;

    @SpyBean
    private SimulacaoEmprestimoRepository simulacaoEmprestimoRepository;

    @Autowired
    private SimulacaoEmprestimoMySQLRepository simulacaoEmprestimoMySQLRepository;

    @Test
    public void givenNoPrepersistedSimulacaoEmprestimo_whenCallBuscarSimulacaoEmprestimo_thenShouldThrowException() {
        // when
        final var actualException = assertThrows(NotFoundException.class,() -> buscarSimulacaoEmprestimoUseCase.buscar("1"));

        // then
        assertEquals("Simulação não encontrada.", actualException.getMessage());
    }

    @Test
    public void givenPrepersistedSimulacaoEmprestimo_whenCallBuscarSimulacaoEmprestimo_thenShouldReturnSimulacaoEmprestimo() {
        // given
        final var simulacaoEmprestimo = SimulacaoEmprestimo.with(
                CPF.with("111.111.111-11"),
                Convenio.EMPRESA_PRIVADA,
                new BigDecimal("124.70"),
                new BigDecimal("1247.00"),
                new BigDecimal("0.0247"),
                10,
                new BigDecimal("1000.00")
        );

        simulacaoEmprestimoMySQLRepository.saveAndFlush(SimulacaoEmprestimoMapper.toEntity(simulacaoEmprestimo));

        // when
        final var result = buscarSimulacaoEmprestimoUseCase.buscar(simulacaoEmprestimo.getIdSimulacao().toString());

        // then
        assertEquals(simulacaoEmprestimo.getIdSimulacao().toString(), result.idSimulacao());
        assertEquals(simulacaoEmprestimo.getCpfCliente().getValue(), result.cpf());
        assertEquals(simulacaoEmprestimo.getValorParcela(), result.valorParcela());
        assertEquals(simulacaoEmprestimo.getQuantidadeParcelas(), result.quantidadeParcelas());
        assertEquals(simulacaoEmprestimo.getValorEmprestimo(), result.valorTotal());
        assertEquals(simulacaoEmprestimo.getDataSimulacao(), result.dataSimulacao());
        assertEquals(simulacaoEmprestimo.getTaxaJuros(), result.taxaJuros().stripTrailingZeros());
        assertEquals(simulacaoEmprestimo.getValorSolicitado(), result.valorSolicitado());

        Mockito.verify(simulacaoEmprestimoRepository, Mockito.times(1))
                .findById(simulacaoEmprestimo.getIdSimulacao().toString());
    }
}
