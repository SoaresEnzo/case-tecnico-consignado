package dev.enzosoares.consignado.simulacao.simulacaoemprestimo;

import dev.enzosoares.consignado.simulacao.cliente.valueobject.CPF;
import dev.enzosoares.consignado.simulacao.cliente.valueobject.Convenio;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public class SimulacaoEmprestimo {
    private final UUID idSimulacao;
    private final Instant dataSimulacao;
    private final CPF cpfCliente;
    private final Convenio convenioCliente;
    private final BigDecimal valorEmprestimo;
    private final BigDecimal valorParcela;
    private final Integer quantidadeParcelas;
    private final BigDecimal taxaJuros;
    private final BigDecimal valorSolicitado;

    private SimulacaoEmprestimo(final UUID idSimulacao,
                                final CPF cpfCliente,
                                final Convenio convenioCliente,
                                final Instant dataSimulacao,
                                final BigDecimal valorParcela,
                                final BigDecimal valorEmprestimo,
                                final BigDecimal taxaJuros,
                                final Integer quantidadeParcelas,
                                final BigDecimal valorSolicitado
    ) {
        this.idSimulacao = idSimulacao;
        this.cpfCliente = cpfCliente;
        this.convenioCliente = convenioCliente;
        this.dataSimulacao = dataSimulacao;
        this.valorParcela = valorParcela;
        this.valorEmprestimo = valorEmprestimo;
        this.taxaJuros = taxaJuros;
        this.quantidadeParcelas = quantidadeParcelas;
        this.valorSolicitado = valorSolicitado;
    }

    public static SimulacaoEmprestimo with(
            final CPF cpfCliente,
            final Convenio convenioCliente,
            final BigDecimal valorParcela,
            final BigDecimal valorEmprestimo,
            final BigDecimal taxaJuros,
            final Integer quantidadeParcelas,
            final BigDecimal valorSolicitado
    ) {
        return new SimulacaoEmprestimo(
                UUID.randomUUID(),
                cpfCliente,
                convenioCliente,
                Instant.now(),
                valorParcela, valorEmprestimo,
                taxaJuros,
                quantidadeParcelas,
                valorSolicitado
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

    public Convenio getConvenioCliente() {
        return convenioCliente;
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

    public BigDecimal getValorSolicitado() {
        return valorSolicitado;
    }
}
