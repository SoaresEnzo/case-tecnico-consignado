package dev.enzosoares.consignado.simulacao.simulacaoemprestimo;

import dev.enzosoares.consignado.simulacao.cliente.valueobject.CPF;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public class SimulacaoEmprestimo {
    private final UUID idSimulacao;
    private final Instant dataSimulacao;
    private final CPF cpfCliente;
    private final BigDecimal valorEmprestimo;
    private final BigDecimal valorParcela;
    private final Integer quantidadeParcelas;
    private final BigDecimal taxaJuros;

    private SimulacaoEmprestimo(final UUID idSimulacao,
                                final CPF cpfCliente,
                                final Instant dataSimulacao,
                                final BigDecimal valorParcela,
                                final BigDecimal valorEmprestimo,
                                final BigDecimal taxaJuros,
                                final Integer quantidadeParcelas
    ) {
        this.idSimulacao = idSimulacao;
        this.cpfCliente = cpfCliente;
        this.dataSimulacao = dataSimulacao;
        this.valorParcela = valorParcela;
        this.valorEmprestimo = valorEmprestimo;
        this.taxaJuros = taxaJuros;
        this.quantidadeParcelas = quantidadeParcelas;
    }

    public static SimulacaoEmprestimo with(
            final CPF cpfCliente,
            final BigDecimal valorParcela,
            final BigDecimal valorEmprestimo,
            final BigDecimal taxaJuros,
            final Integer quantidadeParcelas
    ) {
        return new SimulacaoEmprestimo(
                UUID.randomUUID(),
                cpfCliente,
                Instant.now(),
                valorParcela, valorEmprestimo,
                taxaJuros,
                quantidadeParcelas
        );
    }

    public UUID getIdSimulacao() {
        return idSimulacao;
    }

    public BigDecimal getValorEmprestimo() {
        return valorEmprestimo;
    }

    public CPF getCpfCliente() {
        return cpfCliente;
    }

    public Instant getDataSimulacao() {
        return dataSimulacao;
    }

    public BigDecimal getValorParcela() {
        return valorParcela;
    }

    public Integer getQuantidadeParcelas() {
        return quantidadeParcelas;
    }

    public BigDecimal getTaxaJuros() {
        return taxaJuros;
    }
}
