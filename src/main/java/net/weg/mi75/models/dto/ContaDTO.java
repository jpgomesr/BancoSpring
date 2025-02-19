package net.weg.mi75.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import net.weg.mi75.models.entity.Conta;

public record ContaDTO(
        @NotBlank
        String titular,
        @PositiveOrZero @NotNull
        Double limite,
        @Positive @NotNull
        Integer numero) {
    public Conta convert() {
        return Conta.builder().numero(numero).limite(limite).titular(titular).build();
    }
}
