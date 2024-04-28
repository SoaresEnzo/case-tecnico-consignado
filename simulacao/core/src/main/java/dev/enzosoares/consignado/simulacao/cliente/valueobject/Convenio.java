package dev.enzosoares.consignado.simulacao.cliente.valueobject;

import java.math.BigDecimal;

public enum Convenio {

    EMPRESA_PRIVADA("EP") {
        @Override
        public BigDecimal getTaxa() {
            return new BigDecimal("0.026");
        }

        @Override
        public String toString() {
            return "Empresa Privada";
        }
    },
    ORGAO_PUBLICO("OP") {
        @Override
        public BigDecimal getTaxa() {
            return new BigDecimal("0.022");
        }

        @Override
        public String toString() {
            return "Orgão Público";
        }
    },
    INSS("INSS") {
        @Override
        public BigDecimal getTaxa() {
            return new BigDecimal("0.016");
        }
    };

    private final String key;

    Convenio(final String key) {
        this.key = key;
    }

    public static Convenio getConvenioByKey(String key) {
        for (Convenio convenio : Convenio.values()) {
            if (convenio.getKey().equals(key)) {
                return convenio;
            }
        }
        throw new RuntimeException("Convenio não encontrado para a chave: " + key);
    }

    public abstract BigDecimal getTaxa();

    public String getKey() {
        return key;
    }
}