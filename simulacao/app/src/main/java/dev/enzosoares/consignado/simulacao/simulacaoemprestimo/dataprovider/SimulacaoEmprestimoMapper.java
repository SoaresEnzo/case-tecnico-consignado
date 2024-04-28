package dev.enzosoares.consignado.simulacao.simulacaoemprestimo.dataprovider;

import dev.enzosoares.consignado.simulacao.cliente.valueobject.CPF;
import dev.enzosoares.consignado.simulacao.cliente.valueobject.Convenio;
import dev.enzosoares.consignado.simulacao.simulacaoemprestimo.SimulacaoEmprestimo;

public class SimulacaoEmprestimoMapper {

    public static SimulacaoEmprestimoJpaEntity toEntity(SimulacaoEmprestimo simulacaoEmprestimo) {
        return new SimulacaoEmprestimoJpaEntity(
                simulacaoEmprestimo.getIdSimulacao().toString(),
                simulacaoEmprestimo.getDataSimulacao(),
                simulacaoEmprestimo.getCpfCliente().getValue(),
                simulacaoEmprestimo.getConvenioCliente().getKey(),
                simulacaoEmprestimo.getValorSolicitado(),
                simulacaoEmprestimo.getTaxaJuros(),
                simulacaoEmprestimo.getQuantidadeParcelas(),
                simulacaoEmprestimo.getValorParcela(),
                simulacaoEmprestimo.getValorEmprestimo()
        );
    }

    public static SimulacaoEmprestimo toDomain(SimulacaoEmprestimoJpaEntity simulacaoEmprestimoEntity) {
        return SimulacaoEmprestimo.of(
                simulacaoEmprestimoEntity.getId(),
                simulacaoEmprestimoEntity.getDataSimulacao(),
                CPF.with(simulacaoEmprestimoEntity.getCpf()),
                Convenio.getConvenioByKey(simulacaoEmprestimoEntity.getConvenio()),
                simulacaoEmprestimoEntity.getValorParcela(),
                simulacaoEmprestimoEntity.getValorTotal(),
                simulacaoEmprestimoEntity.getTaxaAplicada(),
                simulacaoEmprestimoEntity.getQuantidadeParcelas(),
                simulacaoEmprestimoEntity.getValorSolicitado()
        );
    }
}
