package br.com.alex.frasesmusicais.service;

import br.com.alex.frasesmusicais.model.enums.JasperReportEnum;
import br.com.alex.frasesmusicais.repository.FraseRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.*;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class RelatorioService{

    @Autowired
    private FraseRepository repository;
    @Autowired
    private DataSource dataSource;
    @Autowired
    private ResourceLoader resourceLoader;

    public void gerarReportJasper(HttpServletResponse response) throws IOException, JRException {
        byte[] imgCabecalho = obterIMG();
        Map<String, Object> params = new HashMap<>();
        params.put("img_cabecalho", imgCabecalho);

        log.info("Preparando arquivo...");
        Resource file = resourceLoader.getResource(JasperReportEnum.FRASES.getNomeRalatorio());
        log.info("Preparando compilação...");

        JasperReport report = JasperCompileManager.compileReport(file.getInputStream());
//        List<Frase> frases = this.repository.buscarFrasesArtistaSelecionado(3L);        // para enviar objeto pronto para relatorio
//        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(frases); // para enviar objeto pronto para relatorio
        log.info("Preparando preenchimento do relatório...");
        Connection connection = DataSourceUtils.getConnection(dataSource);
        JasperPrint print = JasperFillManager.fillReport(report, params, connection);
        log.info("Exportando relatório...");
        JasperExportManager.exportReportToPdfStream(print, response.getOutputStream());
        log.info("Relatório finalizado!");

    }

    private byte[] obterIMG() throws IOException {
        String image = resourceLoader.getResource(JasperReportEnum.FRASES.getImgHeader()).getFile().getAbsolutePath();
        try(InputStream stream = new FileInputStream(new File(image))){
            return IOUtils.toByteArray(stream);
        }catch(Exception e){
            log.error(e.getMessage());
        }
        return null;
    }
}
