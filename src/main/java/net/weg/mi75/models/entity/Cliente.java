package net.weg.mi75.models.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor // Construtor com todos os parâmetros
@NoArgsConstructor // Construtor sem parâmetros
@Data // Data para criar todos os gets e sets e tbm um toString padrão
@Entity
@Table(name = "tb_cliente")
@Builder
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer conta;
    @NonNull
    private String nome;
    @NonNull
    private String cpf;

    @Override
    public String toString() {
        return "\nCliente: " + id +
                "\nNome: " + nome +
                "\nCPF: " + cpf +
                "\n";
    }

}