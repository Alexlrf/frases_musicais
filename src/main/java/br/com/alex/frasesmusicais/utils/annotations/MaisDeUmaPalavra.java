package br.com.alex.frasesmusicais.utils.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MaisDeUmaPalavraClass.class)
@Repeatable(MaisDeUmaPalavra.List.class)
public @interface MaisDeUmaPalavra {
    String message() default "deve conter mais de uma palavra";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.FIELD})
    @interface List {
        MaisDeUmaPalavra[] value();
    }

}
