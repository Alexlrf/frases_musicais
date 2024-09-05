package br.com.alex.frasesmusicais.model.dto;

import br.com.alex.frasesmusicais.model.enums.JasperReportEnum;
import br.com.alex.frasesmusicais.utils.annotations.EnumValido;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JasperRequestDTO {

    @EnumValido(enumClass = JasperReportEnum.class)
    @JsonProperty(value = "tipo_relatorio")
    private String tipoRelatorio;

}
