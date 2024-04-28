package dev.enzosoares.consignado.simulacao.simulacaoemprestimo.usecase.impl;

import dev.enzosoares.consignado.simulacao.cliente.Cliente;
import dev.enzosoares.consignado.simulacao.cliente.dataprovider.ClienteRepository;
import dev.enzosoares.consignado.simulacao.cliente.valueobject.CPF;
import dev.enzosoares.consignado.simulacao.cliente.valueobject.Convenio;
import dev.enzosoares.consignado.simulacao.cliente.valueobject.Segmento;
import dev.enzosoares.consignado.simulacao.simulacaoemprestimo.dataprovider.SimulacaoEmprestimoRepository;
import dev.enzosoares.consignado.simulacao.simulacaoemprestimo.usecase.dto.SimularEmprestimoConsignadoInput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class SimularEmprestimoConsignadoUseCaseImplTest {
    @InjectMocks
    private SimularEmprestimoConsignadoUseCaseImpl simularEmprestimoConsignadoUseCaseImpl;

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private SimulacaoEmprestimoRepository simulacaoEmprestimoRepository;

    @BeforeEach
    void cleanUp() {
        Mockito.reset(clienteRepository, simulacaoEmprestimoRepository);
    }

    @Test
    public void givenAVarejoClienteWithConvenioEP_whenCalcularTaxaJurosCliente_thenShouldReturnTaxaWithDiscount() {
        final var expectedTaxa = new BigDecimal("0.0247");

        // Given
        Cliente umCliente = Cliente.with(CPF.with("222.222.222-22"), "Michael Jackson", true, Segmento.VAREJO, Convenio.EMPRESA_PRIVADA);

        // When
        BigDecimal taxaJurosCliente = SimularEmprestimoConsignadoUseCaseImpl.calcularTaxaJurosCliente(umCliente);

        // Then
        assertEquals(expectedTaxa, taxaJurosCliente);
    }

    @Test
    public void givenAnUniclassClienteWithConvenioOP_whenCalcularTaxaJurosCliente_thenShouldReturnTaxaWithDiscount() {
        final var expectedTaxa = new BigDecimal("0.0209");

        // Given
        Cliente umCliente = Cliente.with(
                CPF.with("222.222.222-22"),
                "Michael Jackson",
                true,
                Segmento.UNICLASS,
                Convenio.ORGAO_PUBLICO);

        // When
        BigDecimal taxaJurosCliente = SimularEmprestimoConsignadoUseCaseImpl.calcularTaxaJurosCliente(umCliente);

        // Then
        assertEquals(expectedTaxa, taxaJurosCliente);
    }

    @Test
    public void givenAClienteWithConvenioINSSAndNoSegmento_whenCalcularTaxaJurosCliente_thenShouldReturnTaxaWithDiscount() {
        final var expectedTaxa = new BigDecimal("0.016");

        // Given
        Cliente umCliente = Cliente.with(
                CPF.with("222.222.222-22"),
                "Michael Jackson",
                false,
                null,
                Convenio.INSS);

        // When
        BigDecimal taxaJurosCliente = SimularEmprestimoConsignadoUseCaseImpl.calcularTaxaJurosCliente(umCliente);

        // Then
        assertEquals(expectedTaxa, taxaJurosCliente);
    }

    @Test
    public void givenAClienteWithSegmentoUniclass_whenCalcularPrazoMaximoSimulacaoCliente_thenShouldReturnPrazoMesesSimulacao() {
        final var expectedPrazo = 36;

        // Given
        Cliente umCliente = Cliente.with(
                CPF.with("222.222.222-22"),
                "Michael Jackson",
                true,
                Segmento.UNICLASS,
                Convenio.EMPRESA_PRIVADA);

        // When
        Integer prazoMaximoSimulacaoCliente = SimularEmprestimoConsignadoUseCaseImpl.calcularPrazoMaximoSimulacaoCliente(umCliente);

        // Then
        assertEquals(expectedPrazo, prazoMaximoSimulacaoCliente);
    }

    @Test
    public void givenAClienteWithSegmentoVarejo_whenCalcularPrazoMaximoSimulacaoCliente_thenShouldReturnPrazoMesesSimulacao() {
        final var expectedPrazo = 24;

        // Given
        Cliente umCliente = Cliente.with(
                CPF.with("222.222.222-22"),
                "Michael Jackson",
                true,
                Segmento.VAREJO,
                Convenio.EMPRESA_PRIVADA);

        // When
        Integer prazoMaximoSimulacaoCliente = SimularEmprestimoConsignadoUseCaseImpl.calcularPrazoMaximoSimulacaoCliente(umCliente);

        // Then
        assertEquals(expectedPrazo, prazoMaximoSimulacaoCliente);
    }

    @Test
    public void givenAClienteWithSegmentoPerson_whenCalcularPrazoMaximoSimulacaoCliente_thenShouldReturnPrazoMesesSimulacao() {
        final var expectedPrazo = 48;

        // Given
        Cliente umCliente = Cliente.with(
                CPF.with("222.222.222-22"),
                "Michael Jackson",
                true,
                Segmento.PERSON,
                Convenio.EMPRESA_PRIVADA);

        // When
        Integer prazoMaximoSimulacaoCliente = SimularEmprestimoConsignadoUseCaseImpl.calcularPrazoMaximoSimulacaoCliente(umCliente);

        // Then
        assertEquals(expectedPrazo, prazoMaximoSimulacaoCliente);
    }

    @Test
    public void givenAClienteNaoCorrentista_whenCalcularPrazoMaximoSimulacaoCliente_thenShouldReturnPrazoMesesSimulacao() {
        final var expectedPrazo = 12;

        // Given
        Cliente umCliente = Cliente.with(
                CPF.with("222.222.222-22"),
                "Michael Jackson",
                false,
                null,
                Convenio.EMPRESA_PRIVADA);

        // When
        Integer prazoMaximoSimulacaoCliente = SimularEmprestimoConsignadoUseCaseImpl.calcularPrazoMaximoSimulacaoCliente(umCliente);

        // Then
        assertEquals(expectedPrazo, prazoMaximoSimulacaoCliente);
    }

    @Test
    public void givenAnInvalidPrazo_whenSimularEmprestimoConsignado_thenShouldThrowException() {
        //given
        final var anInput = new SimularEmprestimoConsignadoInput(
                "222.222.222-22",
                new BigDecimal("1000.00"),
                90
        );
        final var expectedMessage = "O número máximo de parcelas permitido para o segmento do cliente é 12";
        final var aCliente = Cliente.with(
                CPF.with("222.222.222-22"),
                "Michael Jackson",
                false,
                null,
                Convenio.EMPRESA_PRIVADA
        );

        Mockito.when(clienteRepository.findByCpf(aCliente.getCpf())).thenReturn(Optional.of(aCliente));

        //when
        final var anException = assertThrows(Exception.class, () -> simularEmprestimoConsignadoUseCaseImpl.simular(anInput));
        //then
        assertEquals(expectedMessage, anException.getMessage());
    }

    @Test
    public void givenAValidInput_whenSimularEmprestimoConsignado_thenShouldReturnSimulacaoEmprestimoOutput() {
        //given
        final var anInput = new SimularEmprestimoConsignadoInput(
                "222.222.222-22",
                new BigDecimal("5000.00"),
                36
        );
        final var aCliente = Cliente.with(
                CPF.with("222.222.222-22"),
                "Michael Jackson",
                true,
                Segmento.UNICLASS,
                Convenio.ORGAO_PUBLICO
        );

        Mockito.when(clienteRepository.findByCpf(aCliente.getCpf())).thenReturn(Optional.of(aCliente));

        //when
        final var aSimulacaoEmprestimoOutput = simularEmprestimoConsignadoUseCaseImpl.simular(anInput);

        //then
        assertEquals(new BigDecimal("243.39"), aSimulacaoEmprestimoOutput.valorParcela());
        assertEquals(new BigDecimal("8762.00"), aSimulacaoEmprestimoOutput.valorTotal());
    }

    @Test
    public void givenAnInexistentCliente_whenSimularEmprestimoConsignado_thenShouldThrowError() {
        //given
        final var anInput = new SimularEmprestimoConsignadoInput(
                "222.222.222-22",
                new BigDecimal("5000.00"),
                36
        );
        final var aCliente = Cliente.with(
                CPF.with("222.222.222-22"),
                "Michael Jackson",
                true,
                Segmento.UNICLASS,
                Convenio.ORGAO_PUBLICO
        );
        final var expectedMessage = "Cliente não encontrado";

        Mockito.when(clienteRepository.findByCpf(aCliente.getCpf())).thenThrow(new RuntimeException(expectedMessage));

        //when
        var anException = assertThrows(RuntimeException.class, () -> simularEmprestimoConsignadoUseCaseImpl.simular(anInput));

        //then
        assertEquals(expectedMessage, anException.getMessage());
    }

    @Test
    void givenANullSegmento_whenCalcularPrazoMaximoSimulacaoCliente_shouldReturnDefaultPrazo() {
        final var expectedPrazo = 12;
        // Given
        Cliente umCliente = Cliente.with(
                CPF.with("222.222.222-22"),
                "Michael Jackson",
                false,
                null,
                Convenio.EMPRESA_PRIVADA);

        // When
        Integer prazoMaximoSimulacaoCliente = SimularEmprestimoConsignadoUseCaseImpl.calcularPrazoMaximoSimulacaoCliente(umCliente);

        // Then
        assertEquals(expectedPrazo, prazoMaximoSimulacaoCliente);
    }
}