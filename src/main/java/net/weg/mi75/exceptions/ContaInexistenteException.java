package net.weg.mi75.exceptions;

public class ContaInexistenteException extends ContaException {

    public ContaInexistenteException() {
        super("Conta inexistente!"); // Pode colocar qualquer coisa dentro do super, fazendo textos personalizados
    }

}
