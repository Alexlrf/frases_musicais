package br.com.alex.frasesmusicais.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
@Data
@EqualsAndHashCode
public class ArtistaDTO implements Serializable {

    private Long idArtista;

    @NotNull
    @Size(min = 2, max = 100)
    private String nome;
}
