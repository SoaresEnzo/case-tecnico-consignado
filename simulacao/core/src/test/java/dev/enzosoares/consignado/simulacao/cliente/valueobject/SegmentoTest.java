package dev.enzosoares.consignado.simulacao.cliente.valueobject;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class SegmentoTest {

    @Test
    public void givenAnInvalidKey_whenGettingPrazo_theShouldReturn12() {
        final var expectedValue = 12;

        final var segmento = Segmento.valueOf(null);
        assertEquals(expectedValue, segmento.prazoMesesSimulacao());
    }
}