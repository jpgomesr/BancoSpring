package net.weg.mi75.models.dto;

public record ContaResponseDTO(
        Integer id,
        Integer numero,
        Double saldo,
        Double limite,
        ClienteContaGetResponseDTO titular
) {
}
