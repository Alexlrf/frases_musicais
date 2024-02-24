package br.com.alex.frasesmusicais.model.enums;

public enum ResponseGenericoEnum {

    SUCESSO("Sucesso ao excluir registro"),
    ERRO("Erro ao excluir registro");

    private final String mensagemExclusao;

    ResponseGenericoEnum(String msgExclusao) {
        this.mensagemExclusao = msgExclusao;
    }

    public String getMensagemExclusao() {
        return this.mensagemExclusao;
    }
}
