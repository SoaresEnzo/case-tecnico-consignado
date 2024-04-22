package dev.enzosoares.consignado.simulacao.cliente;

import dev.enzosoares.consignado.simulacao.cliente.valueobject.CPF;
import dev.enzosoares.consignado.simulacao.cliente.valueobject.Convenio;
import dev.enzosoares.consignado.simulacao.cliente.valueobject.Segmento;

public class Cliente {
    private CPF cpf;
    private String nome;
    private Boolean correntista;
    private Segmento segmento;
    private Convenio convenio;

    private Cliente(CPF cpf, String nome, Boolean correntista, Segmento segmento, Convenio convenio) {
        this.cpf = cpf;
        this.nome = nome;
        this.correntista = correntista;
        this.segmento = segmento;
        this.convenio = convenio;
    }

    public static Cliente with(final CPF cpf, final String nome, final Boolean correntista, final Segmento segmento, final Convenio convenio) {
        return new Cliente(cpf, nome, correntista, segmento, convenio);
    }

    public CPF getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public Boolean isCorrentista() {
        return correntista;
    }

    public Segmento getSegmento() {
        return segmento;
    }

    public Convenio getConvenio() {
        return convenio;
    }
}
