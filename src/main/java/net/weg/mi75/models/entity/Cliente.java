package net.weg.mi75.models.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import net.weg.mi75.models.dto.ClienteContaGetResponseDTO;
import net.weg.mi75.models.dto.ClienteResponseDTO;
import net.weg.mi75.models.dto.ContaClienteResponseDTO;

import java.util.*;

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
    @NonNull
    private String nome;
    @NonNull
    private Long cpf;
    //    @ManyToMany
//    @JoinTable(
//            name = "tb_cliente_conta",
//            joinColumns = @JoinColumn(name = "cliente_id"),
//            inverseJoinColumns = @JoinColumn(name = "conta_id")
//    )
//    private List<Conta> contas;
    @OneToMany(mappedBy = "titular", cascade = CascadeType.ALL, orphanRemoval = true)
    @Nullable
    private List<Conta> contas;

    public List<Conta> getContas() {
        if (contas != null) {
            return Collections.unmodifiableList(contas);
        }
        return new ArrayList<>();
    }

    public void addConta(@NotNull Conta conta) {
        if (this.contas.contains(conta)) {
            throw new RuntimeException();
        }
        this.contas.add(conta);
    }

    public void rmConta(@NotNull Conta conta) {
        this.contas.remove(conta);
    }

    public ClienteContaGetResponseDTO convertClienteContaGetResponseDTO() {
        return new ClienteContaGetResponseDTO(this.id, this.nome, this.cpf);
    }

    public ClienteResponseDTO convertToClienteResponseDTO() {
        List<ContaClienteResponseDTO> contasDto = Optional.ofNullable(this.contas)
                .orElse(Collections.emptyList()) // Se for null, usa um Set vazio
                .stream()
                .map(Conta::convertToContaClienteResponseDTO).toList();

        return new ClienteResponseDTO(this.id, this.nome, this.cpf, contasDto);
    }
}