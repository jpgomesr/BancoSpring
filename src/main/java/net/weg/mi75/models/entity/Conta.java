package net.weg.mi75.models.entity;

import jakarta.persistence.*;
import lombok.*;
import net.weg.mi75.exceptions.*;

@AllArgsConstructor // Construtor com todos os parâmetros (TA INUTIL AQ, PQ TUDO TEM NONNULL)
@NoArgsConstructor // Construtor sem parâmetros
@Data // Data para criar todos os gets e sets e tbm um toString padrão
@Entity
@Table(name = "tb_conta")
@Builder
public class Conta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Fala que o atributo é auto increment
    @ToString.Exclude // Tira número do toString padrão
    @NonNull // Por padrão vem desativado, mas @NonNull avisa para o RequiredArgsConstructor que precisa dessa variável
    private Integer id;
    @Column(nullable = false, unique = true)
    @NonNull
    private Integer numero;
    @ToString.Include // Por padrão vem ativo, mostra a variável abaixo no toString padrão
    @Column(nullable = false)
    @NonNull
    private String titular;
    @NonNull
    private Double saldo;
    @NonNull
    private Double limite;

    @Override
    public String toString() {
        return "\nConta: " + numero +
                "\nTitular: " + titular +
                "\nSaldo: " + saldo +
                "\nLimite: " + limite +
                "\n";
    }
}