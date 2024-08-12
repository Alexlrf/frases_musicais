package br.com.alex.frasesmusicais.controller;

import br.com.alex.frasesmusicais.model.dto.ArtistaDTO;
import br.com.alex.frasesmusicais.model.dto.ResponseGenericoDTO;
import br.com.alex.frasesmusicais.model.enums.ResponseGenericoEnum;
import br.com.alex.frasesmusicais.service.ArtistaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

    @GetMapping
    @Operation(summary = "Retorna artistas", description = "Retorna artistas cadastrados",
        responses = {
                @ApiResponse(description = "Sucesso na operação", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseGenericoDTO.class))),
        }
    )
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
