package net.weg.mi75.models.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import net.weg.mi75.models.entity.Cliente;
import net.weg.mi75.models.entity.Conta;

public record ContaPutRequestDTO(
        @NotNull
        Cliente titular,
        @NotNull
        Integer numero,
        @PositiveOrZero @NotNull
        Double limite) {
    public Conta convert() {
        return Conta.builder().limite(this.limite).numero(this.numero).titular(this.titular).build();
    }
}
