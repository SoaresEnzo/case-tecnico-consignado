package dev.enzosoares.consignado.simulacao.core.simulacaoemprestimo.usecase;

import dev.enzosoares.consignado.simulacao.ApplicationTest;
import dev.enzosoares.consignado.simulacao.cliente.Cliente;
import dev.enzosoares.consignado.simulacao.cliente.dataprovider.ClienteMapper;
import dev.enzosoares.consignado.simulacao.cliente.dataprovider.ClienteMySQLRepository;
import dev.enzosoares.consignado.simulacao.cliente.valueobject.CPF;
import dev.enzosoares.consignado.simulacao.cliente.valueobject.Convenio;
import dev.enzosoares.consignado.simulacao.cliente.valueobject.Segmento;
import dev.enzosoares.consignado.simulacao.errors.BadRequestException;
import dev.enzosoares.consignado.simulacao.errors.NotFoundException;
import dev.enzosoares.consignado.simulacao.simulacaoemprestimo.dataprovider.SimulacaoEmprestimoMySQLRepository;
import dev.enzosoares.consignado.simulacao.simulacaoemprestimo.dataprovider.SimulacaoEmprestimoRepository;
import dev.enzosoares.consignado.simulacao.simulacaoemprestimo.usecase.SimularEmprestimoConsignadoUseCase;
import dev.enzosoares.consignado.simulacao.simulacaoemprestimo.usecase.dto.SimularEmprestimoConsignadoInput;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@ApplicationTest
public class SimularEmprestimoConsignadoUseCaseIT {
    @Autowired
    private SimularEmprestimoConsignadoUseCase simularEmprestimoConsignadoUseCase;

    @SpyBean
    private SimulacaoEmprestimoRepository simulacaoEmprestimoRepository;

    @Autowired
    private ClienteMySQLRepository clienteMySQLRepository;

    @Test
    public void givenNoClienteInDatabase_whenCallSimularEmprestimoConsignado_thenShouldThrowException() {
        // given
        final var anInput = new SimularEmprestimoConsignadoInput("111.111.111-11", new BigDecimal("1000.00"), 10);
        // when
        final var actualException = assertThrows(NotFoundException.class,() -> simularEmprestimoConsignadoUseCase.simular(anInput));

        // then
        assertEquals("Cliente não encontrado", actualException.getMessage());
    }

    @Test
    public void givenAValidInput_whenCallSimularEmprestimoConsignado_thenShouldReturnSimulacaoEmprestimo() {
        // given
        final var aCliente = Cliente.with(
                CPF.with("111.111.111-11"),
                "LeBron James",
                true,
                Segmento.VAREJO,
                Convenio.EMPRESA_PRIVADA
        );
        clienteMySQLRepository.saveAndFlush(ClienteMapper.toEntity(aCliente));

        final var anInput = new SimularEmprestimoConsignadoInput("111.111.111-11", new BigDecimal("1000.00"), 10);

        // when
        final var result = simularEmprestimoConsignadoUseCase.simular(anInput);

        // then
        assertNotNull(result);
        assertEquals(aCliente.getCpf().getValue(), result.cpf());
        assertEquals(aCliente.getConvenio().toString(), result.convenio());
        assertEquals(anInput.valorSolicitado(), result.valorSolicitado());
        assertEquals(anInput.quantidadeParcelas(), result.quantidadeParcelas());
        assertEquals(new BigDecimal("1247.00"), result.valorTotal());
        assertEquals(new BigDecimal("124.70"), result.valorParcela());
        assertEquals(new BigDecimal("0.0247"), result.taxaJuros());

        Mockito.verify(simulacaoEmprestimoRepository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    public void givenAnInvalidAmountOfParcelas_whenCallSimularEmprestimoConsignado_thenShouldThrowException() {
        // given
        final var aCliente = Cliente.with(
                CPF.with("111.111.111-11"),
                "LeBron James",
                true,
                Segmento.VAREJO,
                Convenio.EMPRESA_PRIVADA
        );
        clienteMySQLRepository.saveAndFlush(ClienteMapper.toEntity(aCliente));

        final var anInput = new SimularEmprestimoConsignadoInput("111.111.111-11", new BigDecimal("1000.00"), 60);

        // when
        final var actualException = assertThrows(BadRequestException.class,() -> simularEmprestimoConsignadoUseCase.simular(anInput));

        // then
        assertEquals("O número máximo de parcelas permitido para o segmento do cliente é 24", actualException.getMessage());
    }

}
