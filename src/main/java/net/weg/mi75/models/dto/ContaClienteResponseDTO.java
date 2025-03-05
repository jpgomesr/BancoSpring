package net.weg.mi75.models.dto;

public record ContaClienteResponseDTO(
        Integer id,
        Double saldo,
        Double limite,
        Integer numero) {
}
