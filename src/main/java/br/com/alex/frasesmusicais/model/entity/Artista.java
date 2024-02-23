package br.com.alex.frasesmusicais.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Entity
@Data
@EqualsAndHashCode
public class Artista implements Serializable {

    @Id
    @Column(name = "id_artista")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idArtista;

    @NotNull
    @Column(unique=true)
    @Size(min = 2, max = 100)
    private String nome;
}
