package br.com.alex.frasesmusicais.service;

import br.com.alex.frasesmusicais.model.dto.JasperRequestDTO;
import br.com.alex.frasesmusicais.model.enums.JasperReportEnum;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.*;
import java.sql.Connection;
import java.util.Map;

@Service
@Slf4j
public class RelatorioService{
    @Autowired
    private ResourceLoader resourceLoader;
    @Autowired
    private DataSource dataSource;

    public void gerarRelatorioJasper(HttpServletResponse response, JasperRequestDTO request) throws IOException, JRException {
        Resource file = resourceLoader.getResource(
                            JasperReportEnum.valueOf(request.getTipoRelatorio()).getPathRelatorio()
                            .concat(File.separator)
                            .concat(request.getNomeRelatorio())
                            .concat(".jrxml")
                        );
        JasperReport report = JasperCompileManager.compileReport(file.getInputStream());
        Map<String, Object> params = request.getParametros();
        Connection connection = DataSourceUtils.getConnection(dataSource);
        JasperPrint print = JasperFillManager.fillReport(report, params, connection);
        JasperExportManager.exportReportToPdfStream(print, response.getOutputStream());
    }
}
