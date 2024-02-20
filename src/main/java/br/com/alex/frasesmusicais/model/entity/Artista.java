package br.com.alex.frasesmusicais.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode
public class Artista implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_artista")
    private Long idArtista;
    private String nome;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "artista")
    private List<Frase> frases;
}
