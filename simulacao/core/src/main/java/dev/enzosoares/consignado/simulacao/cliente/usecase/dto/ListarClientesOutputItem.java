package dev.enzosoares.consignado.simulacao.cliente.usecase.dto;

import dev.enzosoares.consignado.simulacao.cliente.Cliente;

public record ListarClientesOutputItem(
        String cpf,
        String nome,
        String convenio,
        String segmento,
        Boolean correntista
) {
    public static ListarClientesOutputItem from(final Cliente cliente) {
        return new ListarClientesOutputItem(
                cliente.getCpf().getValue(),
                cliente.getNome(),
                cliente.getConvenio().toString(),
                cliente.getSegmento().toString(),
                cliente.isCorrentista()
        );
    }
}
