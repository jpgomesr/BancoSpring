package net.weg.mi75.models.dto;

import jakarta.annotation.Nullable;

import java.util.List;

public record ClienteResponseDTO(
        Integer id,
        String nome,
        Long cpf,
        @Nullable
        List<ContaClienteResponseDTO> contas) {
}
