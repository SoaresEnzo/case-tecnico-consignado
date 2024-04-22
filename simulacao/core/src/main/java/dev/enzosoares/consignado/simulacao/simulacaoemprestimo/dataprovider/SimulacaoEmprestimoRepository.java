package dev.enzosoares.consignado.simulacao.simulacaoemprestimo.dataprovider;

import dev.enzosoares.consignado.simulacao.simulacaoemprestimo.SimulacaoEmprestimo;

import java.util.List;

public interface SimulacaoEmprestimoRepository {

    List<SimulacaoEmprestimo> findAll();
    void save(SimulacaoEmprestimo simulacaoEmprestimo);
}
