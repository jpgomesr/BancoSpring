package net.weg.mi75.models.entity;

import jakarta.persistence.*;
import lombok.*;
import net.weg.mi75.models.dto.ClienteContaGetResponseDTO;
import net.weg.mi75.models.dto.ContaClienteResponseDTO;
import net.weg.mi75.models.dto.ContaGetResponseDTO;
import net.weg.mi75.models.dto.ContaResponseDTO;

@AllArgsConstructor // Construtor com todos os parâmetros (TA INUTIL AQ, PQ TUDO TEM NONNULL)
@NoArgsConstructor // Construtor sem parâmetros
@Data // Data para criar todos os gets e sets e tbm um toString padrão
@Entity
@Table(name = "tb_conta")
@Builder
public class Conta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Fala que o atributo é auto increment
    private Integer id;
    @Column(nullable = false, unique = true)
    private Integer numero;
    @ManyToOne
    private Cliente titular;
    @Builder.Default
    private Double saldo = 0.0;
    private Double limite;

    public ContaClienteResponseDTO convertToContaClienteResponseDTO() {
        return new ContaClienteResponseDTO(this.id, this.saldo, this.limite, this.numero);
    }

    public ContaResponseDTO convertToContaResponseDTO() {
        ClienteContaGetResponseDTO titular = this.titular.convertClienteContaGetResponseDTO();
        return new ContaResponseDTO(this.id, this.numero, this.saldo, this.limite, titular);
    }
}