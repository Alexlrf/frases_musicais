package br.com.alex.frasesmusicais.utils.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EnumValidoClass.class)
@Repeatable(EnumValido.List.class)
public @interface EnumValido {
    Class<? extends Enum<?>> enumClass();
    String message() default "valor deve ser: FRASES ou ARTISTAS";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.FIELD})
    @interface List {
        EnumValido[] value();
    }

}