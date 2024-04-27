package dev.enzosoares.consignado.custodia.errors;

public abstract class DomainException extends RuntimeException {
    public DomainException(final String message) {
        super(message, null);
    }
}
