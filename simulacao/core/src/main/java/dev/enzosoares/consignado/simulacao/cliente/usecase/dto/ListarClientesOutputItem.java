package dev.enzosoares.consignado.simulacao.cliente.usecase.dto;

import dev.enzosoares.consignado.simulacao.cliente.Cliente;

import java.util.Optional;

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
                Optional.ofNullable(cliente.getSegmento()).map(Enum::toString).orElse(null),
                cliente.isCorrentista()
        );
    }
}
