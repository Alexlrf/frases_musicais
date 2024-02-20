package br.com.alex.frasesmusicais.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

//@Component
@Slf4j
public class LogUtil {

    public static void erro(String nomeClasse, String nomeMetodo, Throwable exception) {
        log.error(
                String.format("Classe: %s - Método: %s - Exception: %s - Mensagem: %s"
                        , nomeClasse
                        , nomeMetodo
                        , exception.getClass().getName()
                        , exception.getMessage()
                )
        );
    }
}
