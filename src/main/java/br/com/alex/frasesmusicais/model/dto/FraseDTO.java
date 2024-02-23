package br.com.alex.frasesmusicais.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode
public class FraseDTO implements Serializable {
    private Long idFrase;
    private String texto;
    @JsonProperty(value = "nome_musica")
    private String nomeMusica;
    @JsonProperty(value = "link_video")
    private String linkVideo;
    private ArtistaDTO artista;
}
