package dev.enzosoares.consignado.simulacao.errors;

public class BadRequestException extends DomainException {
    public BadRequestException(final String message) {
        super(message);
    }
}
