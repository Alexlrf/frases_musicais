package br.com.alex.frasesmusicais.controller;

import br.com.alex.frasesmusicais.model.dto.ArtistaDTO;
import br.com.alex.frasesmusicais.model.dto.ResponseGenericoDTO;
import br.com.alex.frasesmusicais.model.enums.ResponseGenericoEnum;
import br.com.alex.frasesmusicais.service.ArtistaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "v1/artistas")
@Slf4j
public class ArtistaController extends AbstractResponse{

    @Autowired
    private ArtistaService artistaService;

    @GetMapping(value = "teste")
    public String testarConexao() {
        return "Teste de conexão OK";
    }

    @GetMapping
    public ResponseEntity<ResponseGenericoDTO> buscarArtistas() {
        List<ArtistaDTO> artistasDto = new ArrayList<>();
        try {
            artistasDto = this.artistaService.buscarArtistas();
        } catch(Exception e) {
            log.error(e.getMessage(), e);
        }
        return retornarResponse(artistasDto, ResponseGenericoEnum.SUCESSO_BUSCA, "", HttpStatus.OK);
    }

}
