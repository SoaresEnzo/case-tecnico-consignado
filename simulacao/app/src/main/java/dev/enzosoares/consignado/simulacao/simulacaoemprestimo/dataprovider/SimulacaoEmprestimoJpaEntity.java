package dev.enzosoares.consignado.simulacao.simulacaoemprestimo.dataprovider;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "simulacao_emprestimo")
public class SimulacaoEmprestimoJpaEntity {

    @Id
    private String id;
    @Column(name = "data_simulacao", nullable = false, columnDefinition = "DATETIME(9)")
    private Instant dataSimulacao;
    @Column(name = "cpf", nullable = false)
    private String cpf;
    @Column(nullable = false)
    private String convenio;
    @Column(name = "valor_solicitado", nullable = false, precision = 15, scale = 2)
    private BigDecimal valorSolicitado;
    @Column(name = "taxa_aplicada", nullable = false, precision = 10, scale = 6)
    private BigDecimal taxaAplicada;
    @Column(name = "quantidade_parcelas", nullable = false)
    private Integer quantidadeParcelas;
    @Column(name = "valor_parcela", nullable = false, precision = 15, scale = 2)
    private BigDecimal valorParcela;
    @Column(name = "valor_total", nullable = false, precision = 15, scale = 2)
    private BigDecimal valorTotal;

    public SimulacaoEmprestimoJpaEntity(
            final String id,
            final Instant dataSimulacao,
            final String cpf,
            final String convenio,
            final BigDecimal valorSolicitado,
            final BigDecimal taxaAplicada,
            final Integer quantidadeParcelas,
            final BigDecimal valorParcela,
            final BigDecimal valorTotal
    ) {
        this.id = id;
        this.dataSimulacao = dataSimulacao;
        this.cpf = cpf;
        this.convenio = convenio;
        this.valorSolicitado = valorSolicitado;
        this.taxaAplicada = taxaAplicada;
        this.quantidadeParcelas = quantidadeParcelas;
        this.valorParcela = valorParcela;
        this.valorTotal = valorTotal;
    }

    public SimulacaoEmprestimoJpaEntity() {
    }

    public String getId() {
        return id;
    }

    public Instant getDataSimulacao() {
        return dataSimulacao;
    }

    public String getCpf() {
        return cpf;
    }

    public String getConvenio() {
        return convenio;
    }

    public BigDecimal getValorSolicitado() {
        return valorSolicitado;
    }

    public BigDecimal getTaxaAplicada() {
        return taxaAplicada;
    }

    public Integer getQuantidadeParcelas() {
        return quantidadeParcelas;
    }

    public BigDecimal getValorParcela() {
        return valorParcela;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }
}
