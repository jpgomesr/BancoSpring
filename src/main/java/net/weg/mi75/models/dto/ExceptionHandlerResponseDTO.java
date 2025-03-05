package net.weg.mi75.models.dto;

import java.time.LocalDateTime;

public record ExceptionHandlerResponseDTO(
        String mensagem,
        LocalDateTime horario
) {
}
