package dev.enzosoares.consignado.custodia.simulacaoemprestimo;


import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public class SimulacaoEmprestimo {
    private final UUID idSimulacao;
    private final Instant dataSimulacao;
    private final String cpfCliente;
    private final BigDecimal valorTotal;
    private final BigDecimal valorParcela;
    private final Integer quantidadeParcelas;
    private final BigDecimal taxaJuros;
    private final BigDecimal valorSolicitado;

    private SimulacaoEmprestimo(final UUID idSimulacao,
                                final String cpfCliente,
                                final Instant dataSimulacao,
                                final BigDecimal valorParcela,
                                final BigDecimal valorTotal,
                                final BigDecimal taxaJuros,
                                final Integer quantidadeParcelas,
                                final BigDecimal valorSolicitado
    ) {
        this.idSimulacao = idSimulacao;
        this.cpfCliente = cpfCliente;
        this.dataSimulacao = dataSimulacao;
        this.valorParcela = valorParcela;
        this.valorTotal = valorTotal;
        this.taxaJuros = taxaJuros;
        this.quantidadeParcelas = quantidadeParcelas;
        this.valorSolicitado = valorSolicitado;
    }

    public static SimulacaoEmprestimo of(
            final String uuid,
            final Instant dataSimulacao,
            final String cpfCliente,
            final BigDecimal valorParcela,
            final BigDecimal valorTotal,
            final BigDecimal taxaJuros,
            final Integer quantidadeParcelas,
            final BigDecimal valorSolicitado
    ) {
        return new SimulacaoEmprestimo(
                UUID.fromString(uuid),
                cpfCliente,
                dataSimulacao,
                valorParcela, valorTotal,
                taxaJuros,
                quantidadeParcelas,
                valorSolicitado
        );
    }

    public UUID getIdSimulacao() {
        return idSimulacao;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public String getCpfCliente() {
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

    public BigDecimal getValorSolicitado() {
        return valorSolicitado;
    }
}
