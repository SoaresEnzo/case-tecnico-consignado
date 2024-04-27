package dev.enzosoares.consignado.custodia.errors;

public class BadRequestException extends DomainException {
    public BadRequestException(final String message) {
        super(message);
    }
}
