package net.weg.mi75.models.dto;

public record ContaGetResponseDTO(
        Integer id,
        Integer numero,
        Double saldo,
        Double limite,
        ClienteContaGetResponseDTO titular) {
}
