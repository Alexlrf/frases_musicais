package br.com.alex.frasesmusicais.model.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode
public class FraseDTO implements Serializable {
    private Long idFrase;
    private String texto;
    @JsonBackReference
    private ArtistaDTO artista = new ArtistaDTO();
}
