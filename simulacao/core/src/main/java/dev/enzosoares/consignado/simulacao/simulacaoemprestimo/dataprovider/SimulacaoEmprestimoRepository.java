package dev.enzosoares.consignado.simulacao.simulacaoemprestimo.dataprovider;

import dev.enzosoares.consignado.simulacao.simulacaoemprestimo.SimulacaoEmprestimo;

import java.util.List;
import java.util.Optional;

public interface SimulacaoEmprestimoRepository {

    List<SimulacaoEmprestimo> findAll();

    void save(SimulacaoEmprestimo simulacaoEmprestimo);

    Optional<SimulacaoEmprestimo> findById(String id);
}
