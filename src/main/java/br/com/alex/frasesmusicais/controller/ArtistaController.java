package br.com.alex.frasesmusicais.controller;

import br.com.alex.frasesmusicais.model.dto.ArtistaDTO;
import br.com.alex.frasesmusicais.model.dto.ResponseGenericoDTO;
import br.com.alex.frasesmusicais.model.enums.ResponseGenericoEnum;
import br.com.alex.frasesmusicais.service.ArtistaService;
import br.com.alex.frasesmusicais.utils.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "v1/artistas")
public class ArtistaController extends AbstractResponse{

    @Autowired
    private ArtistaService artistaService;

    @GetMapping(value = "teste")
    public String testarConexao() {
        return "Teste de conexão OK";
    }

    @CrossOrigin("*")
    @GetMapping
    public ResponseEntity<ResponseGenericoDTO> buscarArtistas() {
        List<ArtistaDTO> artistasDto = new ArrayList<>();
        try {
            artistasDto = this.artistaService.buscarArtistas();
        } catch(Exception e) {
            LogUtil.erro(this.getClass().getSimpleName(), "buscarArtistas", e);
        }
        return retornarResponse(artistasDto, ResponseGenericoEnum.SUCESSO_BUSCA, null, HttpStatus.OK);
    }

}
