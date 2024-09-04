package br.com.alex.frasesmusicais.model.enums;

public enum JasperReportEnum {

    FRASES ("classpath:imagens/img_relatorio_cabecalho.png", "classpath:jasper/frases.jrxml");

    JasperReportEnum(String imgHeader, String nomeRelatorio) {
        this.img = imgHeader;
        this.nomeRalatorio = nomeRelatorio;
    }

    public String getImgHeader() {
        return this.img;
    }

    public String getNomeRalatorio() {
        return this.nomeRalatorio;
    }

    private String img;
    private String nomeRalatorio;
}
