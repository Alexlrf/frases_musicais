package br.com.alex.frasesmusicais.model.dto;

import br.com.alex.frasesmusicais.model.entity.Frase;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
@Data
@EqualsAndHashCode
public class ArtistaDTO implements Serializable {
    private Long idArtista;
    private String nome;
    @JsonManagedReference
    private List<Frase> frases = new ArrayList<>();
}
