package dev.enzosoares.consignado.custodia.contrato.dataprovider;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ContratoMySQLRepository extends JpaRepository<ContratoJpaEntity, String> {

}
