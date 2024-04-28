package dev.enzosoares.consignado.simulacao.simulacaoemprestimo.dataprovider;

import dev.enzosoares.consignado.simulacao.simulacaoemprestimo.SimulacaoEmprestimo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class SimulacaoEmprestimoRepositoryImpl implements SimulacaoEmprestimoRepository {
    private final SimulacaoEmprestimoMySQLRepository simulacaoEmprestimoMySQLRepository;


    public SimulacaoEmprestimoRepositoryImpl(
            SimulacaoEmprestimoMySQLRepository simulacaoEmprestimoMySQLRepository
    ) {
        this.simulacaoEmprestimoMySQLRepository = simulacaoEmprestimoMySQLRepository;
    }


    @Override
    public List<SimulacaoEmprestimo> findAll() {
        return this.simulacaoEmprestimoMySQLRepository
                .findAll()
                .stream()
                .map(SimulacaoEmprestimoMapper::toDomain)
                .toList();
    }

    @Override
    public void save(SimulacaoEmprestimo simulacaoEmprestimo) {
        final var jpaEntity = SimulacaoEmprestimoMapper.toEntity(simulacaoEmprestimo);
        this.simulacaoEmprestimoMySQLRepository.save(jpaEntity);
    }

    @Override
    public Optional<SimulacaoEmprestimo> findById(String id) {
        return this.simulacaoEmprestimoMySQLRepository
                .findById(id)
                .map(SimulacaoEmprestimoMapper::toDomain);
    }
}
