package dev.enzosoares.consignado.custodia.contrato;

import java.time.Instant;
import java.util.UUID;

public class Contrato {
    private final String idContrato;
    private final Instant dataContratacao;
    private final String idSimulacao;

    private Contrato(
            final String idContrato,
            final Instant dataContratacao,
            final String idSimulacao) {
        this.idContrato = idContrato;
        this.dataContratacao = dataContratacao;
        this.idSimulacao = idSimulacao;
    }

    public static Contrato of(
            final String idContrato,
            final Instant dataContratacao,
            final String idSimulacao
    ) {
        return new Contrato(idContrato, dataContratacao, idSimulacao);
    }

    public static Contrato with(
            final String idSimulacao
    ) {
        return new Contrato(UUID.randomUUID().toString(), Instant.now(), idSimulacao);
    }

    public String getIdContrato() {
        return idContrato;
    }

    public String getIdSimulacao() {
        return idSimulacao;
    }

    public Instant getDataContratacao() {
        return dataContratacao;
    }
}
