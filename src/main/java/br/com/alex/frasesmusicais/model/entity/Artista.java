package br.com.alex.frasesmusicais.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Optional;

import br.com.alex.frasesmusicais.model.dto.ArtistaDTO;

@Entity
@Data
@EqualsAndHashCode
@NoArgsConstructor
public class Artista implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @Column(name = "id_artista")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idArtista;

    @NotNull
    @Column(unique=true)
    @Size(min = 2, max = 100)
    private String nome;
    
    public Artista(ArtistaDTO artistaDto) {
    	if(Optional.ofNullable(artistaDto.getIdArtista()).isPresent()) {
    		this.idArtista = artistaDto.getIdArtista();
    	}
    	this.nome = artistaDto.getNome();
    }
    
}
