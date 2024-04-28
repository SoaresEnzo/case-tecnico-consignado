package dev.enzosoares.consignado.simulacao.simulacaoemprestimo.entrypoint.rest.requests;

import dev.enzosoares.consignado.simulacao.simulacaoemprestimo.usecase.dto.SimularEmprestimoConsignadoInput;

import java.math.BigDecimal;


public record SimularEmprestimoConsignadoRequest(
        String cpf,
        BigDecimal valorSolicitado,
        Integer quantidadeParcelas
) {
    public static SimularEmprestimoConsignadoInput toUseCaseInput(
            SimularEmprestimoConsignadoRequest request
    ) {
        return new SimularEmprestimoConsignadoInput(
                request.cpf(),
                request.valorSolicitado(),
                request.quantidadeParcelas()
        );
    }
}
