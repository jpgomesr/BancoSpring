package net.weg.mi75.exceptions;

public class ContaException extends RuntimeException {

    public ContaException(String message) {
        super("Exceção encontrada: " + message);
    }

}
