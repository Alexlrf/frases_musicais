package br.com.alex.frasesmusicais.utils.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StringComConteudoClass implements ConstraintValidator<StringComConteudo, String> {
    @Override
    public boolean isValid(String campo, ConstraintValidatorContext constraintValidatorContext) {
        return !campo.trim().isBlank();
    }
}
