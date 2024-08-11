package br.com.alex.frasesmusicais.controller;

import br.com.alex.frasesmusicais.model.dto.FraseDTO;
import br.com.alex.frasesmusicais.model.dto.ResponseGenericoDTO;
import br.com.alex.frasesmusicais.model.enums.ResponseGenericoEnum;
import br.com.alex.frasesmusicais.service.FraseService;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "v1/frases")
@Slf4j
public class FraseController extends AbstractResponse{

    @Autowired
    private FraseService fraseService;

    @GetMapping(value = "teste")
    public String testarConexao() {
        return "Teste de conexão OK";
    }

    @PostMapping
    public ResponseEntity<ResponseGenericoDTO> incluirFrase(@Valid @RequestBody FraseDTO fraseDTO) {
        try {
            FraseDTO dto = this.fraseService.incluirFrase(fraseDTO);
            return retornarResponse(dto, ResponseGenericoEnum.SUCESSO_INCLUSAO, "", HttpStatus.CREATED);
        } catch(ConstraintViolationException e) {
            log.error(e.getMessage(), e);
            return retornarResponse(null, ResponseGenericoEnum.ERRO_INCLUSAO, "Erro de informações", HttpStatus.BAD_REQUEST);
        }catch(Exception e) {
            log.error(e.getMessage(), e);
            return retornarResponse(null, ResponseGenericoEnum.ERRO_INCLUSAO, e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<ResponseGenericoDTO> buscarFrases() {
        List<FraseDTO> frasesDto = new ArrayList<>();
        try {
            frasesDto = this.fraseService.buscarFrases();
        } catch(Exception e) {
            log.error(e.getMessage(), e);
        }
        return retornarResponse(frasesDto, ResponseGenericoEnum.SUCESSO_BUSCA, "", HttpStatus.OK);
    }

    @GetMapping(value = "/paginado")
    public ResponseEntity<ResponseGenericoDTO> buscarFrasesPaginado(Pageable pageable) {
        List<FraseDTO> frasesDto = new ArrayList<>();
        try {
            frasesDto = this.fraseService.buscarFrasesPaginado(pageable);
        } catch(Exception e) {
            log.error(e.getMessage(), e);
        }
        return retornarResponse(frasesDto, ResponseGenericoEnum.SUCESSO_BUSCA, "", HttpStatus.OK);
    }

    @GetMapping(value = "/{idFrase}")
    public ResponseEntity<ResponseGenericoDTO> buscarFrase(@PathVariable Long idFrase) {
        try {
            FraseDTO fraseDto = this.fraseService.buscarFrase(idFrase);
            return retornarResponse(fraseDto, ResponseGenericoEnum.SUCESSO_BUSCA, "", HttpStatus.OK);
        } catch(Exception e) {
            log.error(e.getMessage(), e);
            return retornarResponse(null, ResponseGenericoEnum.ERRO_BUSCA, e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<ResponseGenericoDTO> alterarFrase(@Valid @RequestBody FraseDTO fraseDTO) {
        try {
            FraseDTO dto = this.fraseService.alterarFrase(fraseDTO);
            return retornarResponse(dto, ResponseGenericoEnum.SUCESSO_ALTERACAO, null, HttpStatus.OK);
        } catch(Exception e) {
            log.error(e.getMessage(), e);
            return retornarResponse(null, ResponseGenericoEnum.ERRO_ALTERACAO, e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/{idFrase}")
    public ResponseEntity<ResponseGenericoDTO> deletarFrase(@PathVariable Long idFrase) {
        try {
            this.fraseService.deletarFrase(idFrase);
            return retornarResponse(null, ResponseGenericoEnum.SUCESSO_EXCLUSAO, "", HttpStatus.OK);
        } catch(Exception e) {
            log.error(e.getMessage(), e);
            return retornarResponse(null, ResponseGenericoEnum.ERRO_EXCLUSAO, e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping(value = "/artista/{idArtista}")
    public ResponseEntity<ResponseGenericoDTO> buscarFrasesArtistaSelecionado(@PathVariable Long idArtista) {
        List<FraseDTO> frasesDto = null;
        try {
            frasesDto = this.fraseService.buscarFrasesArtistaSelecionado(idArtista);
        } catch(Exception e) {
            log.error(e.getMessage(), e);
        }
        return retornarResponse(frasesDto, ResponseGenericoEnum.SUCESSO_BUSCA, "", HttpStatus.OK);
    }


    @GetMapping(value = "/fragmento/{fragmento}")
    public ResponseEntity<ResponseGenericoDTO> buscarFrasesPorTrecho(@PathVariable String fragmento) {
        List<FraseDTO> frasesDto = null;
        try {
            frasesDto = this.fraseService.buscarFrasesPorTrecho(fragmento);
        } catch(Exception e) {
            log.error(e.getMessage(), e);
        }
        return retornarResponse(frasesDto, ResponseGenericoEnum.SUCESSO_BUSCA, "", HttpStatus.OK);
    }

}
