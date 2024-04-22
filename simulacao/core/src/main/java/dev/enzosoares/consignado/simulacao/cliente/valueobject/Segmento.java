package dev.enzosoares.consignado.simulacao.cliente.valueobject;

public enum Segmento {
    VAREJO("V") {
        public Integer prazoMesesSimulacao() {
            return 24;
        }
    },
    UNICLASS("U") {
        public Integer prazoMesesSimulacao() {
            return 36;
        }
    },
    PERSON("P") {
        public Integer prazoMesesSimulacao() {
            return 48;
        }
    };

    public abstract Integer prazoMesesSimulacao();

    private final String key;

    Segmento(final String key) {
        this.key = key;
    }
}
