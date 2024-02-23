package br.com.alex.frasesmusicais.controller;

import br.com.alex.frasesmusicais.exception.CadastroException;
import br.com.alex.frasesmusicais.model.dto.FraseDTO;
import br.com.alex.frasesmusicais.service.FraseService;
import br.com.alex.frasesmusicais.utils.LogUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "v1/frases")
public class FraseController {

    @Autowired
    private FraseService fraseService;

    @GetMapping(value = "teste")
    public String testarConexao() {
        return "Teste de conexão OK";
    }

    @PostMapping
    public ResponseEntity<FraseDTO> incluirFrase(@Valid @RequestBody FraseDTO fraseDTO) {
        try {
            FraseDTO dto = this.fraseService.incluirFrase(fraseDTO);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch(Exception e) {
            LogUtil.erro(this.getClass().getSimpleName(), "incluirFrase", e);
            throw new CadastroException(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<FraseDTO>> buscarFrases() {
        try {
            List<FraseDTO> frasesDto = this.fraseService.buscarFrases();
            return new ResponseEntity<>(frasesDto, HttpStatus.OK);
        } catch(Exception e) {
            LogUtil.erro(this.getClass().getSimpleName(), "buscarFrases", e);
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
        }
    }

    @GetMapping(value = "/{idFrase}")
    public ResponseEntity<FraseDTO> buscarFrase(@PathVariable Long idFrase) {
        try {
            FraseDTO fraseDto = this.fraseService.buscarFrase(idFrase);
            return new ResponseEntity<>(fraseDto, HttpStatus.OK);
        } catch(Exception e) {
            LogUtil.erro(this.getClass().getSimpleName(), "buscarFrase", e);
            return new ResponseEntity<>(new FraseDTO(), HttpStatus.OK);
        }
    }

}
