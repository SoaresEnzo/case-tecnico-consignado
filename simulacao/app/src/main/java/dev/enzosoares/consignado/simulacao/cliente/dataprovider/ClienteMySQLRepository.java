package dev.enzosoares.consignado.simulacao.cliente.dataprovider;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteMySQLRepository extends JpaRepository<ClienteJpaEntity, String> {
}
