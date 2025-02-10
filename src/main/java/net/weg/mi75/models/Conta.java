package net.weg.mi75.models;

import lombok.*;
import net.weg.mi75.exceptions.*;

@AllArgsConstructor // Construtor com todos os parâmetros
@NoArgsConstructor // Construtor sem parâmetros
@Data // Data para criar todos os gets e sets e tbm um toString padrão
public class Conta {
    @ToString.Exclude // Tira número do toString padrão
    private int numero;
    @ToString.Include // Por padrão vem ativo, mostra a variável abaixo no toString padrão
    @NonNull // Por padrão vem desativado, mas @NonNull avisa para o RequiredArgsConstructor que precisa dessa variável
    private Cliente titular;
    private double saldo;
    @NonNull
    private double limite;

    public void saque(double valor)
            throws ValorInvalidoException, SaldoInsuficienteException,
            LimiteInsuficienteException {
        validaValor(valor);
        validaSaldo(valor);
        validaLimite(valor);
        this.saldo -= valor;
    }

    public void deposito(double valor)
            throws ValorInvalidoException {
        validaValor(valor);
        this.saldo += valor;
    }

    public void transferencia(double valor, Conta conta)
            throws ContaInexistenteException, PropriaContaException,
            ValorInvalidoException, SaldoInsuficienteException,
            LimiteInsuficienteException {
        validaConta(conta);
        this.saque(valor);
        conta.deposito(valor);
    }

    private void validaValor(double valor) throws ValorInvalidoException {
        if (valor <= 0) {
            throw new ValorInvalidoException();
        }
    }

    private void validaSaldo(double valor) throws SaldoInsuficienteException {
        if (this.getSaldo() < valor) {
            throw new SaldoInsuficienteException();
        }
    }

    private void validaLimite(double valor) throws LimiteInsuficienteException {
        if (this.getLimite() < valor) {
            throw new LimiteInsuficienteException();
        }
    }

    private void validaConta(Conta conta) throws ContaInexistenteException, PropriaContaException {
        validaContaNula(conta);
        validaContaPropria(conta);
    }

    private void validaContaNula(Conta conta) throws ContaInexistenteException {
        if (conta == null) {
            throw new ContaInexistenteException();
        }
    }

    private void validaContaPropria(Conta conta) throws PropriaContaException {
        if (this.getNumero() == conta.getNumero()) {
            throw new PropriaContaException();
        }
    }

    @Override
    public String toString() {
        return "\nConta: " + numero +
                "\nTitular: " + titular +
                "\nSaldo: " + saldo +
                "\nLimite: " + limite +
                "\n";
    }
}