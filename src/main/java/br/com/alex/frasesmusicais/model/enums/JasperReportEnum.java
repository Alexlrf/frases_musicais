package br.com.alex.frasesmusicais.model.enums;

public enum JasperReportEnum {

    ARTISTAS ("classpath:jasper/artistas"),
    FRASES ("classpath:jasper/frases");

    JasperReportEnum(String pathRelatorio) {
        this.pathRelatorio = pathRelatorio;
    }

    public String getPathRelatorio() {
        return this.pathRelatorio;
    }

    private String pathRelatorio;
}
