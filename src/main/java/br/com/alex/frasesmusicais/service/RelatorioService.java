package br.com.alex.frasesmusicais.service;

import br.com.alex.frasesmusicais.model.dto.JasperRequestDTO;
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

    public void gerarReportJasper(HttpServletResponse response, JasperRequestDTO request) throws IOException, JRException {

        Resource file = resourceLoader.getResource(JasperReportEnum.valueOf(request.getTipoRelatorio()).getNomeRalatorio());
        JasperReport report = JasperCompileManager.compileReport(file.getInputStream());

        Map<String, Object> params = request.getParametros();
        String idsArtistas = request.getParametros().get("ids_artistas").toString().replace("\"","");
        byte[] imgCabecalho = obterIMG(JasperReportEnum.valueOf(request.getTipoRelatorio()).getImgHeader());
        params.put("img_cabecalho", imgCabecalho);
        params.put("P_ID_ARTISTAS", idsArtistas);

        Connection connection = DataSourceUtils.getConnection(dataSource);
        JasperPrint print = JasperFillManager.fillReport(report, params, connection);
        JasperExportManager.exportReportToPdfStream(print, response.getOutputStream());
    }

    private byte[] obterIMG(String tipoRelatorio) throws IOException {
        String image = resourceLoader.getResource(tipoRelatorio).getFile().getAbsolutePath();
        try(InputStream stream = new FileInputStream(image)){
            return IOUtils.toByteArray(stream);
        }catch(Exception e){
            log.error(e.getMessage());
        }
        return new byte[0];
    }
}
