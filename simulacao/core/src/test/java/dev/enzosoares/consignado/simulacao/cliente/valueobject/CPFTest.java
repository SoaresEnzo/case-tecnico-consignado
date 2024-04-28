package dev.enzosoares.consignado.simulacao.cliente.valueobject;

import dev.enzosoares.consignado.simulacao.errors.BadRequestException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CPFTest {


    @Test
    void givenAnInvalidValue_whenCreateACPF_thenThrowException() {
        final var aValue = "12345678901";
        final var expectedMessage = "CPF inválido, deve estar no formato DDD.DDD.DDD-DD";

        final var exception = assertThrows(BadRequestException.class, () -> CPF.with(aValue));
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void givenAValidValue_whenCreateACPF_thenReturnCPF() {
        final var aValue = "123.456.789-09";

        final var cpf = CPF.with(aValue);
        assertEquals(aValue, cpf.getValue());
    }

}