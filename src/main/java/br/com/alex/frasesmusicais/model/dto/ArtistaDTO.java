package br.com.alex.frasesmusicais.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
@Data
@EqualsAndHashCode
public class ArtistaDTO implements Serializable {

    private Long idArtista;

    @NotBlank
    @Size(min = 2, max = 100)
    private String nome;
}
