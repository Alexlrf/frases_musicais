package br.com.alex.frasesmusicais.controller;

import br.com.alex.frasesmusicais.model.dto.ResponseGenericoDTO;
import br.com.alex.frasesmusicais.model.enums.ResponseGenericoEnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class AbstractResponse {
    protected ResponseEntity<ResponseGenericoDTO>  retornarResponse(
            Object body, ResponseGenericoEnum resp, Exception msgDetalheErro, HttpStatus status) {
        return new ResponseEntity<>(
            ResponseGenericoDTO.retornaResponse(
                body
                , resp.name()
                , resp.getMensagem()
                , msgDetalheErro
            )
            , status
        );
    }

}
