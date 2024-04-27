package dev.enzosoares.consignado.custodia.contrato.dataprovider;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Instant;

@Entity
@Table(name = "custodia_contrato")
public class ContratoJpaEntity {
    @Id
    @Column(length = 36)
    private String id;
    @Column(nullable = false)
    private Instant dataContrato;
    @Column(nullable = false, length = 36)
    private String idSimulacao;

    public ContratoJpaEntity(
            final String id,
            final Instant dataContrato,
            final String idSimulacao
    ) {
        this.id = id;
        this.dataContrato = dataContrato;
        this.idSimulacao = idSimulacao;
    }

    public ContratoJpaEntity() {
    }

    public String getId() {
        return id;
    }

    public Instant getDataContrato() {
        return dataContrato;
    }

    public String getIdSimulacao() {
        return idSimulacao;
    }

}
