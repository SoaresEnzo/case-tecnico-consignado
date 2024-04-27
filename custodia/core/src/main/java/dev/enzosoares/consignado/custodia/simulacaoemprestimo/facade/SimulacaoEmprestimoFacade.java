package dev.enzosoares.consignado.custodia.simulacaoemprestimo.facade;

import dev.enzosoares.consignado.custodia.simulacaoemprestimo.SimulacaoEmprestimo;

import java.util.Optional;
import java.util.UUID;

public interface SimulacaoEmprestimoFacade {
    Optional<SimulacaoEmprestimo> fetchById(UUID id);
}
