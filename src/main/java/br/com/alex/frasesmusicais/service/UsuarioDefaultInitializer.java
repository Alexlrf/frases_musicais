package br.com.alex.frasesmusicais.service;

import br.com.alex.frasesmusicais.model.entity.Usuario;
import br.com.alex.frasesmusicais.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UsuarioDefaultInitializer  implements CommandLineRunner {
    @Value("${login.usuario.default}")
    private String USUARIO;

    @Value(value = "${senha.usuario.default}")
    private String SENHA;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void run(String... args) throws Exception {
        UserDetails usuario = this.usuarioRepository.findByLogin(this.USUARIO);
        if (usuario == null) {
            this.usuarioRepository.save(
                    new Usuario(
                            this.USUARIO,
                            new BCryptPasswordEncoder().encode(this.SENHA)
                    )
            );
        }
    }
}
