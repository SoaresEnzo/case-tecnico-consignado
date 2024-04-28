package dev.enzosoares.consignado.simulacao.cliente.dataprovider;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "cliente")
public class ClienteJpaEntity {
    @Id
    @Column(name = "cpf")
    private String cpf;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private Boolean correntista;
    @Column(nullable = false)
    private String segmento;
    @Column(nullable = false)
    private String convenio;

    public ClienteJpaEntity(String cpf, String nome, Boolean correntista, String segmento, String convenio) {
        this.cpf = cpf;
        this.nome = nome;
        this.correntista = correntista;
        this.segmento = segmento;
        this.convenio = convenio;
    }

    public ClienteJpaEntity() {
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public Boolean getCorrentista() {
        return correntista;
    }

    public String getSegmento() {
        return segmento;
    }

    public String getConvenio() {
        return convenio;
    }
}
