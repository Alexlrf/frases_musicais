package br.com.alex.frasesmusicais.controller;

import br.com.alex.frasesmusicais.model.dto.FraseDTO;
import br.com.alex.frasesmusicais.model.entity.Frase;
import br.com.alex.frasesmusicais.service.FraseService;
import br.com.alex.frasesmusicais.utils.LogUtil;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "v1/frases")
public class FraseController {

    @Autowired
    private FraseService fraseService;

    @GetMapping(value = "teste")
    public String testarConexao() {
        return "Teste de conexão recebido";
    }

    @PostMapping
    public ResponseEntity<Frase> incluirFrase(@Valid @RequestBody Frase fraseDTO) {
        try {
            Frase dto = this.fraseService.incluirFrase(fraseDTO);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch(Exception e) {
            LogUtil.erro("FraseController", "incluirFrase", e);
        }
        return null;
    }

}
