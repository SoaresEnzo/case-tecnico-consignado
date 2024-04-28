package dev.enzosoares.consignado.simulacao.simulacaoemprestimo.dataprovider;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SimulacaoEmprestimoMySQLRepository extends JpaRepository<SimulacaoEmprestimoJpaEntity, String> {
}
