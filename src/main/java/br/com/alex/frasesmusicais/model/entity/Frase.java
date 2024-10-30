package br.com.alex.frasesmusicais.model.entity;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Optional;

import br.com.alex.frasesmusicais.model.dto.ArtistaDTO;
import br.com.alex.frasesmusicais.model.dto.FraseDTO;

@Entity
@Data
@EqualsAndHashCode
@NoArgsConstructor
public class Frase implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_frase")
    private Long idFrase;

    @NotNull
    @Size(min = 10, max = 250)
    private String texto;

    @NotNull
    @Column(name = "nome_musica")
    @Size(min = 2, max = 100)
    private String nomeMusica;

    @Column(name = "link_video")
    private String linkVideo;

    @Valid
    @NotNull
    @JoinColumn(name = "id_artista")
    @ManyToOne(cascade=CascadeType.PERSIST)
    private Artista artista;
    
    public Frase(FraseDTO fraseDto) {
    	if(Optional.ofNullable(fraseDto.getIdFrase()).isPresent()) {
    		this.idFrase = fraseDto.getIdFrase();
    	}
    	this.texto = fraseDto.getTexto();
    	this.nomeMusica = fraseDto.getNomeMusica();
    	this.linkVideo = fraseDto.getLinkVideo();
    	this.artista = new Artista(fraseDto.getArtista());
    }
    
}
