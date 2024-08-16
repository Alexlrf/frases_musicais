package br.com.alex.frasesmusicais.controller;

import br.com.alex.frasesmusicais.model.dto.ResponseGenericoDTO;
import br.com.alex.frasesmusicais.model.enums.ResponseGenericoEnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class AbstractResponse {
    protected ResponseEntity<ResponseGenericoDTO>  retornarResponseSucesso(Object body, ResponseGenericoEnum resp, HttpStatus status) {
        return new ResponseEntity<>(
            ResponseGenericoDTO.retornaResponse(
                body
                , resp.name()
                , resp.getMensagem()
                , ""
            )
            , status
        );
    }

    protected ResponseEntity<ResponseGenericoDTO>  retornarResponseErro(ResponseGenericoEnum resp, String msgDetalheErro, HttpStatus status) {
        return new ResponseEntity<>(
            ResponseGenericoDTO.retornaResponse(
                null
                , resp.name()
                , resp.getMensagem()
                , msgDetalheErro
            )
            , status
        );
    }

}
