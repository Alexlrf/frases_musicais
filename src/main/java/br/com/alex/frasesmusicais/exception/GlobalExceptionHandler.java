package br.com.alex.frasesmusicais.exception;

import br.com.alex.frasesmusicais.model.dto.ErroValidacaoRequestDto;
import br.com.alex.frasesmusicais.model.dto.ResponseErroRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
@AllArgsConstructor
public class GlobalExceptionHandler {

    private final MessageSource messageSource;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseErroRequestDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception){

        List<Object> errosDto = new ArrayList<>();

        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        fieldErrors.forEach(e -> {
            String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
            ErroValidacaoRequestDto erro = new ErroValidacaoRequestDto(e.getField(), mensagem);
            errosDto.add(erro);
        });
        return new ResponseEntity<>(
                new ResponseErroRequestDto(
                        HttpStatus.BAD_REQUEST.name(),
                        errosDto,
                        HttpStatus.BAD_REQUEST.value()
                ),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(value = CadastroException.class)
    public ResponseEntity<ResponseErroRequestDto> handleCadastroException(CadastroException exception) {
        return new ResponseEntity<>(
                new ResponseErroRequestDto(
                        "Erro ao realizar cadastro"
                        , List.of(exception.getMessage())
                        , HttpStatus.BAD_REQUEST.value()
                ),
                HttpStatus.BAD_REQUEST
        );
    }

}
