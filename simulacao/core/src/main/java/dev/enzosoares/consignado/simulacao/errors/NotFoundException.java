package dev.enzosoares.consignado.simulacao.errors;

public class NotFoundException extends DomainException {
    public NotFoundException(final String message) {
        super(message);
    }
}
