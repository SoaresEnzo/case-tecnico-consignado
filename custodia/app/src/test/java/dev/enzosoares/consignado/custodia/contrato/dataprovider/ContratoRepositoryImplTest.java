package dev.enzosoares.consignado.custodia.contrato.dataprovider;

import dev.enzosoares.consignado.custodia.ApplicationTest;
import dev.enzosoares.consignado.custodia.contrato.Contrato;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ApplicationTest
class ContratoRepositoryImplTest {
    @Autowired
    private ContratoRepositoryImpl contratoRepository;

    @Autowired
    private ContratoMySQLRepository contratoMySQLRepository;

    @Test
    public void givenAListOfPrepersistedContratos_whenFindAll_thenShouldReturnAllContratos() {
        // given
        final var contrato1 = Contrato.with("1");
        final var contrato2 = Contrato.with("2");
        final var contrato3 = Contrato.with("3");
        final var aListOfContratos = List.of(contrato1, contrato2, contrato3);

        aListOfContratos.stream()
                .map(ContratoMapper::toEntity)
                .forEach(contratoMySQLRepository::saveAndFlush);

        // when
        final var result = contratoRepository.findAll();

        // then
        assertEquals(aListOfContratos.size(), result.size());
        for (int i = 0; i < aListOfContratos.size(); i++) {
            assertEquals(aListOfContratos.get(i).getIdContrato(), result.get(i).getIdContrato());
        }
    }

    @Test
    public void givenNoPrepersistedContratos_whenFindAll_thenShouldReturnEmptyList() {
        // given
        contratoMySQLRepository.deleteAll();

        // when
        final var result = contratoRepository.findAll();

        // then
        assertTrue(result.isEmpty());
    }

    @Test
    public void givenAValidContrato_whenCallSave_thenShouldSave() {
        // given
        final var contrato = Contrato.with("1");

        // when
        contratoRepository.save(contrato);

        // then
        final var result = contratoMySQLRepository.findById(contrato.getIdContrato());
        assertTrue(result.isPresent());
        assertEquals(contrato.getIdContrato(), result.get().getId());
    }
}