package dev.enzosoares.consignado.custodia.contrato.dataprovider;

import dev.enzosoares.consignado.custodia.contrato.Contrato;

import java.util.List;

public interface ContratoRepository {
    void save(Contrato contrato);

    List<Contrato> findAll();
}
