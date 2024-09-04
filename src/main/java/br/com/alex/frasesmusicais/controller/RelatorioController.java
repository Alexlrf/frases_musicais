package br.com.alex.frasesmusicais.controller;

import br.com.alex.frasesmusicais.exception.GenericException;
import br.com.alex.frasesmusicais.service.RelatorioService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(value="/relatorio")
@Slf4j
public class RelatorioController {

    @Autowired
    private RelatorioService relatorioService;

    @GetMapping(value="/jasper")
    public void gerarRelatorio(HttpServletResponse response) {
        try {
            log.info("Preparando relatório");
            response.setHeader("Content-Disposition", "inline; filename=MeuRelatorio.pdf");
            response.setContentType("application/pdf");
            relatorioService.gerarReportJasper(response);
        } catch (JRException | IOException e) {
            log.error("Erro ao gerar relatório: {}", e.getMessage());
            throw new GenericException("Erro ao gerar relatório: " + e.getMessage());
        }

    }

}