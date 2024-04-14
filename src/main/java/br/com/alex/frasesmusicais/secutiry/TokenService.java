package br.com.alex.frasesmusicais.secutiry;

import br.com.alex.frasesmusicais.exception.GenericException;
import br.com.alex.frasesmusicais.model.entity.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.secret.jwt.token}")
    private String secretApi;

    public String gerarToken(Usuario usuario) {
        try {
            Algorithm algoritmo = Algorithm.HMAC256(this.secretApi);
            return JWT.create()
                    .withIssuer("API-Frases Musicais")
                    .withSubject(usuario.getLogin())
                    .withClaim("id", usuario.getIdUsuario())
                    .withExpiresAt(definirExpiracaoToken())
                    .sign(algoritmo);
        } catch (JWTCreationException exception){
            throw new GenericException("Erro ao obter token");
        }
    }

    public String validarToken(String token) {
        try {
            Algorithm algoritmo = Algorithm.HMAC256(this.secretApi);
            return JWT.require(algoritmo)
                    .withIssuer("API-Frases Musicais")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception){
            throw new GenericException("Token JWT inválido ou expirado");
        }
    }

    private Instant definirExpiracaoToken() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
