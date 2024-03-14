package br.com.alex.frasesmusicais.controller;

import br.com.alex.frasesmusicais.model.dto.FraseDTO;
import br.com.alex.frasesmusicais.model.dto.ResponseGenericoDTO;
import br.com.alex.frasesmusicais.model.enums.ResponseGenericoEnum;
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
    public ResponseEntity<ResponseGenericoDTO> incluirFrase(@Valid @RequestBody FraseDTO fraseDTO) {
        try {
            FraseDTO dto = this.fraseService.incluirFrase(fraseDTO);
            return new ResponseEntity<>(
                ResponseGenericoDTO.retornaResponse(
                        dto,
                    ResponseGenericoEnum.SUCESSO_INCLUSAO.name()
                    , ResponseGenericoEnum.SUCESSO_INCLUSAO.getMensagem()
                    , null
                ), HttpStatus.CREATED);
        } catch(Exception e) {
            LogUtil.erro(this.getClass().getSimpleName(), "incluirFrase", e);
            return new ResponseEntity<>(
                ResponseGenericoDTO.retornaResponse(
                    null,
                    ResponseGenericoEnum.ERRO_INCLUSAO.name()
                    , ResponseGenericoEnum.ERRO_INCLUSAO.getMensagem()
                    , e
                )
                , HttpStatus.BAD_REQUEST
            );
        }
    }

    @CrossOrigin("*")
    @GetMapping
    public ResponseEntity<ResponseGenericoDTO> buscarFrases() {
        List<FraseDTO> frasesDto = new ArrayList<>();
        try {
            frasesDto = this.fraseService.buscarFrases();
        } catch(Exception e) {
            LogUtil.erro(this.getClass().getSimpleName(), "buscarFrases", e);
        }
        return new ResponseEntity<>(
            ResponseGenericoDTO.retornaResponse(
                frasesDto,
                ResponseGenericoEnum.SUCESSO_BUSCA.name()
                , ResponseGenericoEnum.SUCESSO_BUSCA.getMensagem()
                , null
            )
            , HttpStatus.OK
        );
    }

    @GetMapping(value = "/{idFrase}")
    public ResponseEntity<ResponseGenericoDTO> buscarFrase(@PathVariable Long idFrase) {
        try {
            FraseDTO fraseDto = this.fraseService.buscarFrase(idFrase);
            return new ResponseEntity<>(
                ResponseGenericoDTO.retornaResponse(
                    fraseDto,
                    ResponseGenericoEnum.SUCESSO_BUSCA.name()
                    , ResponseGenericoEnum.SUCESSO_BUSCA.getMensagem()
                    , null
                )
                , HttpStatus.OK
            );
        } catch(Exception e) {
            LogUtil.erro(this.getClass().getSimpleName(), "buscarFrase", e);
            return new ResponseEntity<>(
                ResponseGenericoDTO.retornaResponse(
                    null,
                    ResponseGenericoEnum.ERRO_BUSCA.name()
                    , ResponseGenericoEnum.ERRO_BUSCA.getMensagem()
                    , e
                 )
                , HttpStatus.BAD_REQUEST
            );
        }
    }

    @PutMapping
    public ResponseEntity<ResponseGenericoDTO> alterarFrase(@Valid @RequestBody FraseDTO fraseDTO) {
        try {
            FraseDTO dto = this.fraseService.alterarFrase(fraseDTO);
            return new ResponseEntity<>(
                ResponseGenericoDTO.retornaResponse(
                    dto,
                    ResponseGenericoEnum.SUCESSO_ALTERACAO.name()
                    , ResponseGenericoEnum.SUCESSO_ALTERACAO.getMensagem()
                    , null
                )
                , HttpStatus.OK
            );
        } catch(Exception e) {
            LogUtil.erro(this.getClass().getSimpleName(), "alterarFrase", e);
            return new ResponseEntity<>(
                ResponseGenericoDTO.retornaResponse(
                    null,
                    ResponseGenericoEnum.ERRO_ALTERACAO.name()
                    , ResponseGenericoEnum.ERRO_ALTERACAO.getMensagem()
                    , e
                )
                , HttpStatus.BAD_REQUEST
            );
        }
    }

    @DeleteMapping(value = "/{idFrase}")
    public ResponseEntity<ResponseGenericoDTO> deletarFrase(@PathVariable Long idFrase) {
        try {
            this.fraseService.deletarFrase(idFrase);
            return new ResponseEntity<>(
                ResponseGenericoDTO.retornaResponse(
                    null,
                    ResponseGenericoEnum.SUCESSO_EXCLUSAO.name()
                    , ResponseGenericoEnum.SUCESSO_EXCLUSAO.getMensagem()
                    , null
                )
                , HttpStatus.OK
            );
        } catch(Exception e) {
            LogUtil.erro(this.getClass().getSimpleName(), "deletarFrase", e);
            return new ResponseEntity<>(
                ResponseGenericoDTO.retornaResponse(
                    null,
                    ResponseGenericoEnum.ERRO_EXCLUSAO.name()
                    , ResponseGenericoEnum.ERRO_EXCLUSAO.getMensagem()
                    , e
                )
                , HttpStatus.BAD_REQUEST
            );
        }
    }

    @CrossOrigin("*")
    @GetMapping(value = "/artista/{idArtista}")
    public ResponseEntity<ResponseGenericoDTO> buscarFrasesArtistaSelecionado(@PathVariable Long idArtista) {
        List<FraseDTO> frasesDto = null;
        try {
            frasesDto = this.fraseService.buscarFrasesArtistaSelecionado(idArtista);
        } catch(Exception e) {
            LogUtil.erro(this.getClass().getSimpleName(), "buscarFrasesArtistaSelecionado", e);
        }
        return new ResponseEntity<>(
                ResponseGenericoDTO.retornaResponse(
                        frasesDto,
                        ResponseGenericoEnum.SUCESSO_BUSCA.name()
                        , ResponseGenericoEnum.SUCESSO_BUSCA.getMensagem()
                        , null
                )
                , HttpStatus.OK
        );
    }

}
