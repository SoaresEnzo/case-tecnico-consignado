package dev.enzosoares.consignado.simulacao.simulacaoemprestimo.dataprovider;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface SimulacaoEmprestimoMySQLRepository extends JpaRepository<SimulacaoEmprestimoJpaEntity, String> {
}
