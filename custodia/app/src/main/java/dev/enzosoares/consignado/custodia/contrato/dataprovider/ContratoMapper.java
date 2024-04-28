package dev.enzosoares.consignado.custodia.contrato.dataprovider;

import dev.enzosoares.consignado.custodia.contrato.Contrato;

public class ContratoMapper {
    public static Contrato toDomain(ContratoJpaEntity contratoJpaEntity) {
        return Contrato.of(
                contratoJpaEntity.getId(),
                contratoJpaEntity.getDataContrato(),
                contratoJpaEntity.getIdSimulacao()
        );
    }

    public static ContratoJpaEntity toEntity(Contrato contrato) {
        return new ContratoJpaEntity(
                contrato.getIdContrato(),
                contrato.getDataContratacao(),
                contrato.getIdSimulacao()
        );
    }
}
