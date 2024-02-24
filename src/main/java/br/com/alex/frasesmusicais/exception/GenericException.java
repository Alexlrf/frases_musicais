package br.com.alex.frasesmusicais.exception;

public class GenericException extends RuntimeException{

    public GenericException(String titulo) {
        super(titulo);
    }
}
