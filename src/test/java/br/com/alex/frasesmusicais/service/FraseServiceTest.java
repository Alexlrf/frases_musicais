package br.com.alex.frasesmusicais.service;

import br.com.alex.frasesmusicais.model.dto.FraseDTO;
import br.com.alex.frasesmusicais.model.entity.Artista;
import br.com.alex.frasesmusicais.model.entity.Frase;
import br.com.alex.frasesmusicais.repository.ArtistaRepository;
import br.com.alex.frasesmusicais.repository.FraseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.annotation.ApplicationScope;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class FraseServiceTest {

    @Mock
    private FraseRepository fraseRepository;

    @Mock
    private ArtistaRepository artistaRepository;

    @Autowired
    @InjectMocks
    private FraseService fraseService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void incluirFrase() {
    }

    @Test
    void alterarFrase() {
    }

    @Test
    @DisplayName("Deve retornar uma frase do DB")
    void buscarFraseCase1() {
        Frase fraseDB = obterFrase();
        when(this.fraseRepository.findById(1L)).thenReturn(Optional.of(fraseDB));
        FraseDTO frase = this.fraseService.buscarFrase(1L);
        verify(this.fraseRepository, times(1)).findById(1L);

    }

    @Test
    @DisplayName("Deve lançar uma RuntimeException")
    void buscarFraseCase2() {
        Frase fraseDB = null;
        when(this.fraseRepository.findById(1L)).thenReturn(Optional.ofNullable(fraseDB));
        RuntimeException exc = assertThrows(RuntimeException.class, ()->{
            FraseDTO frase = this.fraseService.buscarFrase(1L);
            verify(this.fraseRepository, times(1)).findById(1L);
        });
        assertEquals("Erro ao buscar frase por ID", exc.getMessage());

    }

    private Frase obterFrase() {
        Artista artista = new Artista();
        artista.setIdArtista(1L);
        artista.setNome("nomeArtista");

        Frase frase = new Frase();
        frase.setIdFrase(1L);
        frase.setLinkVideo("teste_link");
        frase.setTexto("texto");
        frase.setNomeMusica("nomeMusica");
        frase.setArtista(artista);
        return frase;
    }
}