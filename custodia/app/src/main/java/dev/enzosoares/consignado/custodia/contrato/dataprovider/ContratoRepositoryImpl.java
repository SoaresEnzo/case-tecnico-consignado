package dev.enzosoares.consignado.custodia.contrato.dataprovider;

import dev.enzosoares.consignado.custodia.contrato.Contrato;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ContratoRepositoryImpl implements ContratoRepository {
    private final ContratoMySQLRepository contratoMySQLRepository;

    public ContratoRepositoryImpl(ContratoMySQLRepository contratoMySQLRepository) {
        this.contratoMySQLRepository = contratoMySQLRepository;
    }

    @Override
    public void save(Contrato contrato) {
        final var entity = ContratoMapper.toEntity(contrato);
        this.contratoMySQLRepository.save(entity);
    }

    @Override
    public List<Contrato> findAll() {
        return this.contratoMySQLRepository
                .findAll()
                .stream()
                .map(ContratoMapper::toDomain)
                .toList();
    }
}
