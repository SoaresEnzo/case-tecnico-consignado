package dev.enzosoares.consignado.simulacao.errors;

public class DomainException extends RuntimeException {
    public DomainException(final String message) {
        super(message, null);
    }
}
