package br.com.alex.frasesmusicais.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
    private String texto;
    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "id_artista")
    private Artista artista;
}
