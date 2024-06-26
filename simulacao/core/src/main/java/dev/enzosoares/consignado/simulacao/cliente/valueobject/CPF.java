package dev.enzosoares.consignado.simulacao.cliente.valueobject;

import dev.enzosoares.consignado.simulacao.errors.BadRequestException;

import java.util.Objects;

public class CPF {
    private String value;

    private CPF(String value) {
        this.value = value;
    }

    public static CPF with(String value) {
        CPF cpf = new CPF(value);
        cpf.validate();
        return cpf;
    }

    private void validate() {
        if (!value.matches("[0-9]{3}.[0-9]{3}.[0-9]{3}-[0-9]{2}")) {
            throw new BadRequestException("CPF inválido, deve estar no formato DDD.DDD.DDD-DD");
        }
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CPF cpf = (CPF) o;
        return Objects.equals(value, cpf.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
