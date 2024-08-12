package br.com.alex.frasesmusicais.controller;

import br.com.alex.frasesmusicais.model.dto.DadosAutenticacaoDTO;
import br.com.alex.frasesmusicais.model.dto.ResponseGenericoDTO;
import br.com.alex.frasesmusicais.model.dto.TokenDto;
import br.com.alex.frasesmusicais.model.entity.Usuario;
import br.com.alex.frasesmusicais.secutiry.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "v1/frasesMusicais/autenticacao")
@Slf4j
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    @Operation(summary = "Retorna token", description = "Retorna token Bearer após receber credenciais válidas",
        responses = {
                @ApiResponse(description = "Sucesso na operação", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseGenericoDTO.class))),
        }
    )
    public ResponseEntity<TokenDto> realizarLogin(@RequestBody @Valid DadosAutenticacaoDTO autenticacaoDTO) {
        String jwtToken = "";
        try {
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(autenticacaoDTO.getLogin(), autenticacaoDTO.getSenha());
            Authentication authentication = manager.authenticate(authenticationToken);

            jwtToken = tokenService.gerarToken((Usuario) authentication.getPrincipal());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return ResponseEntity.ok(new TokenDto(jwtToken));
    }
}
