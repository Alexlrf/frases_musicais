package br.com.alex.frasesmusicais.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DadosAutenticacaoDTO {

    private String login;
    private String senha;
}
