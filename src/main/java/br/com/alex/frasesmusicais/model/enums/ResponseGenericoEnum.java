package br.com.alex.frasesmusicais.model.enums;

public enum ResponseGenericoEnum {

    SUCESSO_EXCLUSAO("Sucesso ao excluir registro"),
    ERRO_EXCLUSAO("Erro ao excluir registro"),
    SUCESSO_BUSCA("Sucesso ao buscar registro"),
    ERRO_BUSCA("Erro ao buscar registro");

    private final String mensagem;

    ResponseGenericoEnum(String msg) {
        this.mensagem = msg;
    }

    public String getMensagem() {
        return this.mensagem;
    }
}
