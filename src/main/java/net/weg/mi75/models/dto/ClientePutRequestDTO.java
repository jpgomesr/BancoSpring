package net.weg.mi75.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import net.weg.mi75.models.entity.Cliente;
import net.weg.mi75.models.entity.Conta;

import java.util.List;

public record ClientePutRequestDTO(
        @NotBlank
        String nome,
        @NotNull
        Long cpf,
        @NotNull
        List<Conta> contas
) {
    public Cliente convert() {
        return Cliente.builder().nome(this.nome).cpf(this.cpf).contas(this.contas).build();
    }
}
