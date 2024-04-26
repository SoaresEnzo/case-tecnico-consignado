package dev.enzosoares.consignado.simulacao.simulacaoemprestimo.dataprovider;

import dev.enzosoares.consignado.simulacao.cliente.valueobject.CPF;
import dev.enzosoares.consignado.simulacao.cliente.valueobject.Convenio;
import dev.enzosoares.consignado.simulacao.simulacaoemprestimo.SimulacaoEmprestimo;

import java.math.BigDecimal;

public class SimulacaoEmprestimoMapper {

    public static SimulacaoEmprestimoJpaEntity toEntity(SimulacaoEmprestimo simulacaoEmprestimo) {
        return new SimulacaoEmprestimoJpaEntity(
        simulacaoEmprestimo.getIdSimulacao().toString(),
        simulacaoEmprestimo.getDataSimulacao(),
        simulacaoEmprestimo.getCpfCliente().getValue(),
        simulacaoEmprestimo.getConvenioCliente().getKey(),
        simulacaoEmprestimo.getValorSolicitado().doubleValue(),
        simulacaoEmprestimo.getTaxaJuros().doubleValue(),
        simulacaoEmprestimo.getQuantidadeParcelas(),
        simulacaoEmprestimo.getValorParcela().doubleValue(),
        simulacaoEmprestimo.getValorEmprestimo().doubleValue()
        );
    }

    public static SimulacaoEmprestimo toDomain(SimulacaoEmprestimoJpaEntity simulacaoEmprestimoEntity) {
        return SimulacaoEmprestimo.of(
                simulacaoEmprestimoEntity.getId(),
                simulacaoEmprestimoEntity.getDataSimulacao(),
                CPF.with(simulacaoEmprestimoEntity.getCpf()),
                Convenio.getConvenioByKey(simulacaoEmprestimoEntity.getConvenio()),
                BigDecimal.valueOf(simulacaoEmprestimoEntity.getValorParcela()),
                BigDecimal.valueOf(simulacaoEmprestimoEntity.getValorTotal()),
                BigDecimal.valueOf(simulacaoEmprestimoEntity.getTaxaAplicada()),
                simulacaoEmprestimoEntity.getQuantidadeParcelas(),
                BigDecimal.valueOf(simulacaoEmprestimoEntity.getValorSolicitado())
                );
    }
}
