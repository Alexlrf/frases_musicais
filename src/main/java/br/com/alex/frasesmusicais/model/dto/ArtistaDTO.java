package br.com.alex.frasesmusicais.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
@Data
@EqualsAndHashCode
public class ArtistaDTO implements Serializable {
    private Long idArtista;
    private String nome;
}
