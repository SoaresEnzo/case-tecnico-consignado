package dev.enzosoares.consignado.simulacao.cliente.dataprovider;

import dev.enzosoares.consignado.simulacao.cliente.Cliente;
import dev.enzosoares.consignado.simulacao.cliente.valueobject.CPF;
import dev.enzosoares.consignado.simulacao.cliente.valueobject.Convenio;
import dev.enzosoares.consignado.simulacao.cliente.valueobject.Segmento;

public class ClienteMapper {

    public static ClienteJpaEntity toEntity(Cliente cliente) {
        return new ClienteJpaEntity(
                cliente.getCpf().getValue(),
                cliente.getNome(),
                cliente.isCorrentista(),
                cliente.getSegmento().getKey(),
                cliente.getConvenio().getKey()
        );
    }

    public static Cliente toDomain(ClienteJpaEntity clienteEntity) {
        return Cliente.with(
                CPF.with(clienteEntity.getCpf()),
                clienteEntity.getNome(),
                clienteEntity.getCorrentista(),
                Segmento.getSegmentoByKey(clienteEntity.getSegmento()),
                Convenio.getConvenioByKey(clienteEntity.getConvenio())
        );
    }
}
