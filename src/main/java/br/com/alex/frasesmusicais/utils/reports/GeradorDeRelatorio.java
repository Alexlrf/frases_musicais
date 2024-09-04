package br.com.alex.frasesmusicais.utils.reports;

import br.com.alex.frasesmusicais.exception.GenericException;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRSaver;
import net.sf.jasperreports.export.Exporter;

import java.io.OutputStream;
import java.sql.Connection;
import java.util.Map;

@Slf4j
public class GeradorDeRelatorio {

    private Connection conexao;

    public GeradorDeRelatorio(Connection conexao) {
        this.conexao = conexao;
    }

    public void geraPdf(String relatorio, Map<String, Object> parametros, OutputStream saida) {
        try {
            // compila jrxml em memoria
//            JasperReport jasper = JasperCompileManager.compileReport(jrxml);

//            JRSaver.saveObject(jasper, "pessoa.jasper");

            // preenche relatorio
            JasperPrint print = JasperFillManager.fillReport(relatorio, parametros, this.conexao);

            // exporta para pdf
//            Exporter exporter = new JRPdfExporter();
            JasperExportManager.exportReportToPdf(print);
//            JRExporter exporter = new JRPdfExporter();
//            exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
//            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, saida);
//            exporter.exportReport();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new GenericException("Erro ao gerar relatório" + e.getMessage());
        }
    }
}

