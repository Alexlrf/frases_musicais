package br.com.alex.frasesmusicais.controller;

import br.com.alex.frasesmusicais.model.dto.DadosAutenticacaoDTO;
import br.com.alex.frasesmusicais.model.dto.TokenDto;
import br.com.alex.frasesmusicais.model.entity.Usuario;
import br.com.alex.frasesmusicais.secutiry.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "v1/frasesMusicais/autenticacao")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<TokenDto> realizarLogin(@RequestBody @Valid DadosAutenticacaoDTO autenticacaoDTO) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(autenticacaoDTO.getLogin(), autenticacaoDTO.getSenha());
        Authentication authentication = manager.authenticate(authenticationToken);

        String jwtToken = tokenService.gerarToken((Usuario) authentication.getPrincipal());

        return ResponseEntity.ok(new TokenDto(jwtToken));

    }
}
