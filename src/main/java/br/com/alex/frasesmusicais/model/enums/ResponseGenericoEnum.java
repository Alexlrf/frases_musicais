package br.com.alex.frasesmusicais.model.enums;

public enum ResponseGenericoEnum {

    SUCESSO_INCLUSAO("Sucesso ao incluir registro"),
    ERRO_INCLUSAO("Erro ao incluir registro"),
    SUCESSO_BUSCA("Sucesso ao buscar registro"),
    ERRO_BUSCA("Erro ao buscar registro"),

    SUCESSO_ALTERACAO("Sucesso ao alterar registro"),
    ERRO_ALTERACAO("Erro ao alterar registro"),
    SUCESSO_EXCLUSAO("Sucesso ao excluir registro"),
    ERRO_EXCLUSAO("Erro ao excluir registro");

    private final String mensagem;

    ResponseGenericoEnum(String msg) {
        this.mensagem = msg;
    }

    public String getMensagem() {
        return this.mensagem;
    }
}
