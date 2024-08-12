package br.com.alex.frasesmusicais.controller;

import br.com.alex.frasesmusicais.model.dto.FraseDTO;
import br.com.alex.frasesmusicais.model.dto.ResponseGenericoDTO;
import br.com.alex.frasesmusicais.model.enums.ResponseGenericoEnum;
import br.com.alex.frasesmusicais.service.FraseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    @Operation(summary = "Testa conexão com a API", description = "Testa conexão bem sucedida com a API (Teste de conexão OK)",
        responses = {
            @ApiResponse(description = "Sucesso na requisição", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
        }
    )
    public String testarConexao() {
        return "Teste de conexão OK";
    }

    @PostMapping
    @Operation(summary = "Cadastra frase", description = "Cadastra nova frase",
        responses = {
            @ApiResponse(description = "Sucesso na operação", responseCode = "201", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseGenericoDTO.class))),
            @ApiResponse(responseCode = "403", description = "Autenticação requerida", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "401", description = "Falha na autenticação", content = @Content(schema = @Schema(hidden = true)))
        }
    )
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
    @Operation(summary = "Retorna frases", description = "Retorna todas as frases cadastradas",
         responses = {
            @ApiResponse(description = "Sucesso na requisição", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseGenericoDTO.class))),
         }
    )
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
    @Operation(summary = "Retorna busca paginada", description = "Retorna busca paginada utilizando 'page' e 'size'",
        responses = {
            @ApiResponse(description = "Sucesso na operação", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseGenericoDTO.class))),
            @ApiResponse(responseCode = "403", description = "Autenticação requerida", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "401", description = "Falha na autenticação", content = @Content(schema = @Schema(hidden = true)))
        }
    )
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
    @Operation(summary = "Retorna frase", description = "Retorna frase utilizando o ID da frase",
        responses = {
            @ApiResponse(description = "Sucesso na operação", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseGenericoDTO.class))),
            @ApiResponse(responseCode = "403", description = "Autenticação requerida", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "401", description = "Falha na autenticação", content = @Content(schema = @Schema(hidden = true)))
        }
    )
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
    @Operation(summary = "Altera frase", description = "Altera frase cadastrada utilizando o ID da frase",
        responses = {
            @ApiResponse(description = "Sucesso na operação", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseGenericoDTO.class))),
            @ApiResponse(responseCode = "403", description = "Autenticação requerida", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "401", description = "Falha na autenticação", content = @Content(schema = @Schema(hidden = true)))
        }
    )
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
    @Operation(summary = "Deleta frase", description = "Deleta frase cadastrada utilizando o ID da frase",
        responses = {
            @ApiResponse(description = "Sucesso na operação", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseGenericoDTO.class))),
            @ApiResponse(responseCode = "403", description = "Autenticação requerida", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "401", description = "Falha na autenticação", content = @Content(schema = @Schema(hidden = true)))
        }
    )
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
    @Operation(summary = "Busca frases por artista", description = "Busca frases por artista utilizando ID do artista",
        responses = {
            @ApiResponse(description = "Sucesso na operação", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseGenericoDTO.class))),
            @ApiResponse(responseCode = "403", description = "Autenticação requerida", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "401", description = "Falha na autenticação", content = @Content(schema = @Schema(hidden = true)))
        }
    )
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
    @Operation(summary = "Busca frases por fragmento de texto", description = "Busca frases por fragmento de texto",
        responses = {
            @ApiResponse(description = "Sucesso na operação", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseGenericoDTO.class))),
            @ApiResponse(responseCode = "403", description = "Autenticação requerida", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "401", description = "Falha na autenticação", content = @Content(schema = @Schema(hidden = true)))
        }
    )
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
