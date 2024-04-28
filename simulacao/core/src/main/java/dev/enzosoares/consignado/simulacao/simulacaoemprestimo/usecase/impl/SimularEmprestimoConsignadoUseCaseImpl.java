package dev.enzosoares.consignado.simulacao.simulacaoemprestimo.usecase.impl;

import dev.enzosoares.consignado.simulacao.cliente.Cliente;
import dev.enzosoares.consignado.simulacao.cliente.dataprovider.ClienteRepository;
import dev.enzosoares.consignado.simulacao.cliente.valueobject.CPF;
import dev.enzosoares.consignado.simulacao.errors.BadRequestException;
import dev.enzosoares.consignado.simulacao.errors.NotFoundException;
import dev.enzosoares.consignado.simulacao.simulacaoemprestimo.SimulacaoEmprestimo;
import dev.enzosoares.consignado.simulacao.simulacaoemprestimo.dataprovider.SimulacaoEmprestimoRepository;
import dev.enzosoares.consignado.simulacao.simulacaoemprestimo.usecase.SimularEmprestimoConsignadoUseCase;
import dev.enzosoares.consignado.simulacao.simulacaoemprestimo.usecase.dto.SimularEmprestimoConsignadoInput;
import dev.enzosoares.consignado.simulacao.simulacaoemprestimo.usecase.dto.SimularEmprestimoConsignadoOutput;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.MessageFormat;
import java.util.Objects;

@Named
public class SimularEmprestimoConsignadoUseCaseImpl implements SimularEmprestimoConsignadoUseCase {

    private final ClienteRepository clienteRepository;
    private final SimulacaoEmprestimoRepository simulacaoEmprestimoRepository;

    @Inject
    public SimularEmprestimoConsignadoUseCaseImpl(
            final ClienteRepository clienteRepository,
            final SimulacaoEmprestimoRepository simulacaoEmprestimoRepository
    ) {
        this.clienteRepository = Objects.requireNonNull(clienteRepository);
        this.simulacaoEmprestimoRepository = Objects.requireNonNull(simulacaoEmprestimoRepository);
    }

    public static BigDecimal calcularTaxaJurosCliente(Cliente cliente) {
        final var taxaOriginal = cliente
                .getConvenio()
                .getTaxa();

        if (cliente.isCorrentista()) {
            return new BigDecimal("0.95")
                    .multiply(taxaOriginal)
                    .stripTrailingZeros();
        }

        return taxaOriginal.stripTrailingZeros();
    }

    public static Integer calcularPrazoMaximoSimulacaoCliente(Cliente cliente) {
        if (cliente.isCorrentista() && cliente.getSegmento() != null) {
            return cliente.getSegmento().prazoMesesSimulacao();
        }

        return 12;
    }

    @Override
    public SimularEmprestimoConsignadoOutput simular(final SimularEmprestimoConsignadoInput input) {
        final var cpfCliente = CPF.with(input.cpf());
        final var cliente = clienteRepository.findByCpf(cpfCliente)
                .orElseThrow(() -> new NotFoundException("Cliente não encontrado"));

        final var valorSolicitado = input.valorSolicitado();
        final var quantidadeDesejadaParcelas = input.quantidadeParcelas();
        final var taxa = SimularEmprestimoConsignadoUseCaseImpl.calcularTaxaJurosCliente(cliente);
        final var taxaTotal = taxa.multiply(BigDecimal.valueOf(quantidadeDesejadaParcelas)).add(BigDecimal.ONE);
        final var quantidadeMaximaParcelas = SimularEmprestimoConsignadoUseCaseImpl.calcularPrazoMaximoSimulacaoCliente(cliente);

        if (quantidadeDesejadaParcelas > quantidadeMaximaParcelas) {
            throw new BadRequestException(
                    MessageFormat
                            .format("O número máximo de parcelas permitido para o segmento do cliente é de {0} meses", quantidadeMaximaParcelas)
            );
        }

        final var valorFinal = valorSolicitado
                .multiply(taxaTotal)
                .setScale(2, RoundingMode.HALF_EVEN);

        final var valorParcela = valorFinal
                .divide(BigDecimal.valueOf(quantidadeDesejadaParcelas), 2, RoundingMode.HALF_EVEN);

        final var simulacao = SimulacaoEmprestimo.with(
                cpfCliente,
                cliente.getConvenio(),
                valorParcela,
                valorFinal,
                taxa,
                quantidadeDesejadaParcelas,
                valorSolicitado
        );

        this.simulacaoEmprestimoRepository.save(simulacao);

        return new SimularEmprestimoConsignadoOutput(
                simulacao.getIdSimulacao().toString(),
                simulacao.getDataSimulacao(),
                simulacao.getCpfCliente().getValue(),
                simulacao.getConvenioCliente().toString(),
                simulacao.getValorParcela(),
                simulacao.getValorEmprestimo(),
                simulacao.getQuantidadeParcelas(),
                simulacao.getTaxaJuros(),
                simulacao.getValorSolicitado());
    }
}
