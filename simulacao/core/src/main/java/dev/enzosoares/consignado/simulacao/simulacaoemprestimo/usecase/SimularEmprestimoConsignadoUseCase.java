package dev.enzosoares.consignado.simulacao.simulacaoemprestimo.usecase;

import dev.enzosoares.consignado.simulacao.simulacaoemprestimo.usecase.dto.SimularEmprestimoConsignadoInput;
import dev.enzosoares.consignado.simulacao.simulacaoemprestimo.usecase.dto.SimularEmprestimoConsignadoOutput;

public interface SimularEmprestimoConsignadoUseCase {

    SimularEmprestimoConsignadoOutput simular(SimularEmprestimoConsignadoInput input);
}
