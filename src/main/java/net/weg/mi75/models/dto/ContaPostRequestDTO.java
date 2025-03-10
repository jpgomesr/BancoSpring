package net.weg.mi75.models.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import net.weg.mi75.models.entity.Cliente;
import net.weg.mi75.models.entity.Conta;

public record ContaPostRequestDTO(
        @NotNull @Positive
        Integer idTitular,
        @PositiveOrZero @NotNull
        Double limite,
        @Positive @NotNull
        Integer numero) {
    public Conta convert(Cliente cliente) {
        return Conta.builder().numero(numero).limite(limite).titular(cliente).build();
    }
}
