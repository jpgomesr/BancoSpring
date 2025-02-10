package net.weg.mi75.models;

import lombok.*;

@AllArgsConstructor // Construtor com todos os parâmetros
@NoArgsConstructor // Construtor sem parâmetros
@Data // Data para criar todos os gets e sets e tbm um toString padrão
public class Cliente {
    private int id;
    private Conta conta;
    private String nome;
    private String cpf;

    @Override
    public String toString() {
        return "\nCliente: " + id +
                "\nNome: " + nome +
                "\nCPF: " + cpf +
                "\n";
    }

}