package dev.enzosoares.consignado.simulacao.cliente.entrypoint.rest.responses;

import dev.enzosoares.consignado.simulacao.cliente.usecase.dto.ListarClientesOutputItem;

public record ListarClientesResponseItem(
        String cpfCliente,
        String nomeCliente,
        Boolean isCorrentista,
        String segmentoCliente,
        String convenioCliente
) {
    public static ListarClientesResponseItem from(ListarClientesOutputItem item) {
        return new ListarClientesResponseItem(
                item.cpf(),
                item.nome(),
                item.correntista(),
                item.segmento(),
                item.convenio()
        );
    }
}
