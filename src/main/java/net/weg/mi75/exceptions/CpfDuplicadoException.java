package net.weg.mi75.exceptions;

public class CpfDuplicadoException extends ContaException {
    public CpfDuplicadoException() {
        super("CPF já cadastrado!");
    }
}
