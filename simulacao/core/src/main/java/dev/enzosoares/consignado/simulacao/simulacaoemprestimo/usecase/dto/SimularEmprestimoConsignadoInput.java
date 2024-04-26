package dev.enzosoares.consignado.simulacao.simulacaoemprestimo.usecase.dto;

import dev.enzosoares.consignado.simulacao.errors.BadRequestException;

import java.math.BigDecimal;

public record SimularEmprestimoConsignadoInput(
        String cpf,
        BigDecimal valorSolicitado,
        Integer quantidadeParcelas
) {
    public SimularEmprestimoConsignadoInput {
        if (cpf == null || cpf.isBlank()) {
            throw new BadRequestException("CPF não pode ser nulo ou vazio");
        }
        if (valorSolicitado == null || valorSolicitado.compareTo(BigDecimal.ZERO) <= 0) {
            throw new BadRequestException("Valor solicitado não pode ser nulo ou menor ou igual a zero");
        }
        if (quantidadeParcelas == null || quantidadeParcelas <= 0) {
            throw new BadRequestException("Quantidade de parcelas não pode ser nula ou menor ou igual a zero");
        }
    }

}
