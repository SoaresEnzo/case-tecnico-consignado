package dev.enzosoares.consignado.custodia.errors;

public class NotFoundException extends DomainException {
    public NotFoundException(final String message) {
        super(message);
    }
}
