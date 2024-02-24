package br.com.alex.frasesmusicais.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseGenericoDTO {

    @JsonProperty(value = "tipo_retorno")
    private String tipoRetorno;

    private String mensagem;
    @JsonProperty(value = "detalhe_erro")

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String detalheErro;
}
