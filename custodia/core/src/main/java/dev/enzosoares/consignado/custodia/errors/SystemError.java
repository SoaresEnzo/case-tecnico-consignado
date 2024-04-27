package dev.enzosoares.consignado.custodia.errors;

public class SystemError extends DomainException {
    public SystemError(String message) {
        super(message);
    }
}
