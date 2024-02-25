package br.com.alex.frasesmusicais.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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

    private Object body;

    public static ResponseGenericoDTO buscaTodos(List<Object> body, String tipoRetorno, String mensagemBusca, Exception msgDetalheErro) {
        return new ResponseGenericoDTO(
                tipoRetorno
                , mensagemBusca
                , msgDetalheErro != null ? msgDetalheErro.getMessage() : null
                , body
        );
    }

    public static ResponseGenericoDTO busca(Object body, String tipoRetorno, String mensagemBusca, Exception msgDetalheErro) {
        return new ResponseGenericoDTO(
                tipoRetorno
                , mensagemBusca
                , msgDetalheErro != null ? msgDetalheErro.getMessage() : null
                , body
        );
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static ResponseGenericoDTO exclusao(Object body, String tipoRetorno, String mensagemExclusao, Exception msgDetalheErro) {
        return new ResponseGenericoDTO(
                tipoRetorno
                , mensagemExclusao
                , msgDetalheErro != null ? msgDetalheErro.getMessage() : null
                , body
        );
    }

}
