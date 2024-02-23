package br.com.alex.frasesmusicais.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class ResponseErroRequestDto {

    private String title;
    private List<Object> message;
    private int status;
    private final OffsetDateTime timestamp = OffsetDateTime.now();

}
