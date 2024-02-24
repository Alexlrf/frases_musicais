package br.com.alex.frasesmusicais.model.entity;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Entity
@Data
@EqualsAndHashCode
public class Frase implements Serializable {

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
}
