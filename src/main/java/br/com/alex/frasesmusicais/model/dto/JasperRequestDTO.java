package br.com.alex.frasesmusicais.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JasperRequestDTO {

    @JsonProperty(value = "tipo_relatorio")
    private String tipoRelatorio;

}
