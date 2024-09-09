package br.com.alex.frasesmusicais.controller;

import br.com.alex.frasesmusicais.exception.GenericException;
import br.com.alex.frasesmusicais.model.dto.JasperRequestDTO;
import br.com.alex.frasesmusicais.service.RelatorioService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping(value="/relatorio")
public class RelatorioController {

    @Autowired
    private RelatorioService relatorioService;

    @PostMapping(value="/jasper")
    public void gerarRelatorio(HttpServletResponse response, @Valid @RequestBody JasperRequestDTO tipoRelatorio) {
        try {
            response.setHeader("Content-Disposition", "inline; filename=MeuRelatorio.pdf");
            response.setContentType("application/pdf");
            this.relatorioService.gerarReportJasper(response, tipoRelatorio);
        } catch (JRException | IOException e) {
            log.error("Erro ao gerar relatório: {}", e.getMessage());
            throw new GenericException("Erro ao gerar relatório: " + e.getMessage());
        }
    }

}