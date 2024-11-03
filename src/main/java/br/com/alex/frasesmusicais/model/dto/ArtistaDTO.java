package br.com.alex.frasesmusicais.model.dto;

import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Optional;
import br.com.alex.frasesmusicais.model.entity.Artista;

@Data
@EqualsAndHashCode
@NoArgsConstructor
public class ArtistaDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long idArtista;

    @NotBlank
    @Size(min = 2, max = 100)
    private String nome;
    
    public ArtistaDTO(Artista artista) {
    	if(Optional.ofNullable(artista.getIdArtista()).isPresent()) {
    		this.idArtista = artista.getIdArtista();
    	}
    	this.nome = artista.getNome();
    }
}
