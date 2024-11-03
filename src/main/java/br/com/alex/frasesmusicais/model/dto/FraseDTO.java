package br.com.alex.frasesmusicais.model.dto;

import br.com.alex.frasesmusicais.model.entity.Frase;
import br.com.alex.frasesmusicais.utils.annotations.MaisDeUmaPalavra;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Optional;

@Data
@EqualsAndHashCode
@NoArgsConstructor
public class FraseDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;


	private Long idFrase;


    @NotBlank
    @MaisDeUmaPalavra
    @Size(min = 10, max = 250)
    private String texto;

    @NotBlank
    @Size(min = 2, max = 100)
    @JsonProperty(value = "nome_musica")
    private String nomeMusica;

    @JsonProperty(value = "link_video")
    private String linkVideo;

    @Valid
    @NotNull
    private ArtistaDTO artista;
    
    public FraseDTO (Frase frase) {
    	if(Optional.ofNullable(frase.getIdFrase()).isPresent()) {
    		this.idFrase = frase.getIdFrase();
    	}
    	this.texto = frase.getTexto();
    	this.nomeMusica = frase.getNomeMusica();
    	this.linkVideo = frase.getLinkVideo();
    	this.artista = new ArtistaDTO(frase.getArtista());
    }
    
}
