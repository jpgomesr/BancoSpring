package net.weg.mi75.exceptions;

public class ContaJaCadastradaException extends ContaException {

    public ContaJaCadastradaException() {
        super("Já existe uma conta cadastrada com o número informado!");
    }

}
