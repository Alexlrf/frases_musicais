package br.com.alex.frasesmusicais.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
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

    @JsonInclude(value= JsonInclude.Include.NON_EMPTY, content= JsonInclude.Include.NON_EMPTY)
    private List<Object> body;

    public static List<ResponseGenericoDTO> buscaTodos(List<Object> body, String tipoRetorno, String mensagemBusca, Exception msgDetalheErro) {
        return Collections.singletonList(new ResponseGenericoDTO(
                tipoRetorno
                , mensagemBusca
                , msgDetalheErro != null ? msgDetalheErro.getMessage() : null
                , body
        ));
    }

    public static ResponseGenericoDTO busca(Object body, String tipoRetorno, String mensagemBusca, Exception msgDetalheErro) {
        return new ResponseGenericoDTO(
                tipoRetorno
                , mensagemBusca
                , msgDetalheErro != null ? msgDetalheErro.getMessage() : null
                , body != null ? Collections.singletonList(body) : null
        );
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static ResponseGenericoDTO exclusao(Object body, String tipoRetorno, String mensagemExclusao, Exception msgDetalheErro) {
        return new ResponseGenericoDTO(
                tipoRetorno
                , mensagemExclusao
                , msgDetalheErro != null ? msgDetalheErro.getMessage() : null
                , body != null ? new ArrayList<>() : null
        );
    }

}
