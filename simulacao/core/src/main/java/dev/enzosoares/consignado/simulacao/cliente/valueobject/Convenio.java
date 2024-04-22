package dev.enzosoares.consignado.simulacao.cliente.valueobject;

import java.math.BigDecimal;

public enum Convenio {

    EMPRESA_PRIVADA("EP") {
        @Override
        public BigDecimal getTaxa() {
            return new BigDecimal("0.026");
        }
    },
    ORGAO_PUBLICO("OP") {
        @Override
        public BigDecimal getTaxa() {
            return new BigDecimal("0.022");
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

    public abstract BigDecimal getTaxa();

    public String getKey() {
        return key;
    }
}