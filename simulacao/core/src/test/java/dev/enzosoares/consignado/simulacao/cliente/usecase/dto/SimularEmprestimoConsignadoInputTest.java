package dev.enzosoares.consignado.simulacao.cliente.usecase.dto;

import dev.enzosoares.consignado.simulacao.simulacaoemprestimo.usecase.dto.SimularEmprestimoConsignadoInput;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class SimularEmprestimoConsignadoInputTest {

    @Test
    public void givenAValidInput_whenCreatingAnInput_thenShouldCreateIt() {
        final var cpf = "123.123.123-12";
        final var valor = new BigDecimal("1000");
        final var parcelas = 12;

        final var input = new SimularEmprestimoConsignadoInput(cpf, valor, parcelas);
        assertEquals(cpf, input.cpf());
        assertEquals(valor, input.valorSolicitado());
        assertEquals(parcelas, input.quantidadeParcelas());
    }

    @Test
    public void givenANullCPF_whenCreatingAnInput_thenShouldThrowAnException() {
        final String cpf = null;
        final var valor = new BigDecimal("1000");
        final var parcelas = 12;
        final var expectedMessage = "CPF não pode ser nulo ou vazio";

        final var actualException = assertThrows(IllegalArgumentException.class, () ->
                new SimularEmprestimoConsignadoInput(cpf, valor, parcelas));
        assertEquals(expectedMessage, actualException.getMessage());
    }

    @Test
    public void givenAnEmptyCPF_whenCreatingAnInput_thenShouldThrowAnException() {
        final String cpf = "";
        final var valor = new BigDecimal("1000");
        final var parcelas = 12;
        final var expectedMessage = "CPF não pode ser nulo ou vazio";

        final var actualException = assertThrows(IllegalArgumentException.class, () ->
                new SimularEmprestimoConsignadoInput(cpf, valor, parcelas));
        assertEquals(expectedMessage, actualException.getMessage());
    }

    @Test
    public void givenANullValor_whenCreatingAnInput_thenShouldThrowAnException() {
        final var cpf = "123.123.123-12";
        final BigDecimal valor = null;
        final var parcelas = 12;
        final var expectedMessage = "Valor solicitado não pode ser nulo ou menor ou igual a zero";

        final var actualException = assertThrows(IllegalArgumentException.class, () ->
                new SimularEmprestimoConsignadoInput(cpf, valor, parcelas));
        assertEquals(expectedMessage, actualException.getMessage());
    }

    @Test
    public void givenAZeroValor_whenCreatingAnInput_thenShouldThrowAnException() {
        final var cpf = "123.123.123-12";
        final var valor = new BigDecimal("0");
        final var parcelas = 12;
        final var expectedMessage = "Valor solicitado não pode ser nulo ou menor ou igual a zero";

        final var actualException = assertThrows(IllegalArgumentException.class, () ->
                new SimularEmprestimoConsignadoInput(cpf, valor, parcelas));
        assertEquals(expectedMessage, actualException.getMessage());
    }

    @Test
    public void givenANegativeValor_whenCreatingAnInput_thenShouldThrowAnException() {
        final var cpf = "123.123.123-12";
        final var valor = new BigDecimal("-12");
        final var parcelas = 12;
        final var expectedMessage = "Valor solicitado não pode ser nulo ou menor ou igual a zero";

        final var actualException = assertThrows(IllegalArgumentException.class, () ->
                new SimularEmprestimoConsignadoInput(cpf, valor, parcelas));
        assertEquals(expectedMessage, actualException.getMessage());
    }

    @Test
    public void givenANullNumberOfParcelas_whenCreatingAnInput_thenShouldThrowAnException() {
        final var cpf = "123.123.123-12";
        final var valor = new BigDecimal("1000");
        final Integer parcelas = null;
        final var expectedMessage = "Quantidade de parcelas não pode ser nula ou menor ou igual a zero";

        final var actualException = assertThrows(IllegalArgumentException.class, () ->
                new SimularEmprestimoConsignadoInput(cpf, valor, parcelas));
        assertEquals(expectedMessage, actualException.getMessage());
    }

    @Test
    public void givenAZeroNumberOfParcelas_whenCreatingAnInput_thenShouldThrowAnException() {
        final var cpf = "123.123.123-12";
        final var valor = new BigDecimal("1000");
        final var parcelas = 0;
        final var expectedMessage = "Quantidade de parcelas não pode ser nula ou menor ou igual a zero";

        final var actualException = assertThrows(IllegalArgumentException.class, () ->
                new SimularEmprestimoConsignadoInput(cpf, valor, parcelas));
        assertEquals(expectedMessage, actualException.getMessage());
    }

    @Test
    public void givenANegativeNumberOfParcelas_whenCreatingAnInput_thenShouldThrowAnException() {
        final var cpf = "123.123.123-12";
        final var valor = new BigDecimal("1000");
        final var parcelas = -12;
        final var expectedMessage = "Quantidade de parcelas não pode ser nula ou menor ou igual a zero";

        final var actualException = assertThrows(IllegalArgumentException.class, () ->
                new SimularEmprestimoConsignadoInput(cpf, valor, parcelas));
        assertEquals(expectedMessage, actualException.getMessage());
    }
}