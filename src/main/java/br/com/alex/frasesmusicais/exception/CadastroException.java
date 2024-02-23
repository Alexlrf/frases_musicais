package br.com.alex.frasesmusicais.exception;

public class CadastroException extends RuntimeException{

    public CadastroException(String titulo) {
        super(titulo);
    }
}
