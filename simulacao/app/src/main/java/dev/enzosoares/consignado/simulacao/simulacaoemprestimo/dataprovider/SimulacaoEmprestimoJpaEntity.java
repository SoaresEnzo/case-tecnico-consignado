package dev.enzosoares.consignado.simulacao.simulacaoemprestimo.dataprovider;

import dev.enzosoares.consignado.simulacao.cliente.dataprovider.ClienteJpaEntity;
import jakarta.persistence.*;


import java.time.Instant;

@Entity
@Table(name = "simulacao_emprestimo")
public class SimulacaoEmprestimoJpaEntity {

    @Id
    private String id;
    @Column(name = "data_simulacao", nullable = false)
    private Instant dataSimulacao;
    @Column(name = "cpf", nullable = false)
    private String cpf;
    @Column(nullable = false)
    private String convenio;
    @Column(name = "valor_solicitado", nullable = false)
    private Double valorSolicitado;
    @Column(name = "taxa_aplicada", nullable = false)
    private Double taxaAplicada;
    @Column(name = "quantidade_parcelas", nullable = false)
    private Integer quantidadeParcelas;
    @Column(name = "valor_parcela", nullable = false)
    private Double valorParcela;
    @Column(name = "valor_total", nullable = false)
    private Double valorTotal;

    public SimulacaoEmprestimoJpaEntity(
            final String id,
            final Instant dataSimulacao,
            final String cpf,
            final String convenio,
            final Double valorSolicitado,
            final Double taxaAplicada,
            final Integer quantidadeParcelas,
            final Double valorParcela,
            final Double valorTotal
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

    public Double getValorSolicitado() {
        return valorSolicitado;
    }

    public Double getTaxaAplicada() {
        return taxaAplicada;
    }

    public Integer getQuantidadeParcelas() {
        return quantidadeParcelas;
    }

    public Double getValorParcela() {
        return valorParcela;
    }

    public Double getValorTotal() {
        return valorTotal;
    }
}
