package br.com.alex.frasesmusicais.utils.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = StringComConteudoClass.class)
@Repeatable(StringComConteudo.List.class)
public @interface StringComConteudo {
    String message() default "não pode ser vazio ou conter somente espaços";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.FIELD})
    @interface List {
        StringComConteudo[] value();
    }
}
