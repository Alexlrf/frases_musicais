package br.com.alex.frasesmusicais.repository;

import br.com.alex.frasesmusicais.model.entity.Artista;
import br.com.alex.frasesmusicais.model.entity.Frase;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

@DataJpaTest
@ActiveProfiles("test")
class FraseRepositoryTest {

    @Autowired
    private FraseRepository fraseRepository;

    @Autowired
    private EntityManager entityManager;

    private final Frase frase = criarFrase(
                     "texto de teste",
               "Nome musica de teste",
               "nome artista de teste"
                        );

    @Test
    @DisplayName("Deve retornar uma lista de frases do DB")
    void buscarFrasesArtistaSelecionadoCase1() {
        this.salvarFraseDB(this.frase);
        List<Frase> fraseDB = this.fraseRepository.buscarFrasesArtistaSelecionado(this.frase.getArtista().getIdArtista());
        assertThat(fraseDB).isNotEmpty();
    }

    @Test
    @DisplayName("Deve retornar uma lista de frases vazia do DB")
    void buscarFrasesArtistaSelecionadoCase2() {
        List<Frase> fraseDB = this.fraseRepository.buscarFrasesArtistaSelecionado(this.frase.getArtista().getIdArtista());
        assertThat(fraseDB).isEmpty();
    }

    @Test
    @DisplayName("Deve retornar uma lista de frases do DB buscando por fragmento de texto")
    void findByTextoContainingCase1() {
        this.salvarFraseDB(this.frase);
        List<Frase> fraseDB = this.fraseRepository.findByTextoContaining("teste");
        assertThat(fraseDB).isNotEmpty();
    }

    @Test
    @DisplayName("Deve retornar uma lista de frases vazia do DB buscando por fragmento de texto sem inserir frase")
    void findByTextoContainingCase2() {
        List<Frase> fraseDB = this.fraseRepository.findByTextoContaining("outro");
        assertThat(fraseDB).isEmpty();
    }

    @Test
    @DisplayName("Deve retornar uma lista de frases vazia do DB buscando por fragmento de texto diferente do inserido")
    void findByTextoContainingCase3() {
        this.salvarFraseDB(this.frase);
        List<Frase> fraseDB = this.fraseRepository.findByTextoContaining("outro");
        assertThat(fraseDB).isEmpty();
    }

    private Frase criarFrase(String texto, String nomeMusica, String nomeArtista) {
        Frase frase = new Frase();
        frase.setTexto(texto);
        frase.setNomeMusica(nomeMusica);
        Artista artista = new Artista();
        artista.setNome(nomeArtista);
        frase.setArtista(artista);
        return frase;
    }

    private void salvarFraseDB(Frase frase) {
        this.entityManager.persist(frase);
    }


}