package net.weg.mi75.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import net.weg.mi75.models.entity.Cliente;

public record ClientePostRequestDTO(
        @NotBlank
        String nome,
        @NotNull
        Long cpf
) {
    public Cliente convert() {
        return Cliente.builder().nome(this.nome).cpf(this.cpf).build();
    }
}
