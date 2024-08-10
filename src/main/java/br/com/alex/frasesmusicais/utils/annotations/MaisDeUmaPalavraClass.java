package br.com.alex.frasesmusicais.utils.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class MaisDeUmaPalavraClass implements ConstraintValidator<MaisDeUmaPalavra, String> {
    @Override
    public boolean isValid(String campo, ConstraintValidatorContext constraintValidatorContext) {
        return campo.split(" ").length > 1;
    }
}
