package br.com.alex.frasesmusicais.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode
public class FraseDTO implements Serializable {

    private Long idFrase;

    @NotNull
    @Size(min = 10, max = 250)
    private String texto;

    @NotNull
    @Size(min = 2, max = 100)
    @JsonProperty(value = "nome_musica")
    private String nomeMusica;

    @JsonProperty(value = "link_video")
    private String linkVideo;

    @Valid
    @NotNull
    private ArtistaDTO artista;
}
