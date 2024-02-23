package br.com.alex.frasesmusicais.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErroValidacaoRequestDto {

    private String campo;
    private String erro;
}
