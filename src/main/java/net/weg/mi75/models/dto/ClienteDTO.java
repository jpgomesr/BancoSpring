package net.weg.mi75.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import net.weg.mi75.models.entity.Cliente;

public record ClienteDTO(
        @NotBlank @Positive
        Integer conta,
        @NotBlank
        String nome,
        @NotBlank
        String cpf
) {
    public Cliente convert() {
        return Cliente.builder().conta(conta).nome(nome).cpf(cpf).build();
    }
}
