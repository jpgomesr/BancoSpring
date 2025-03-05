package net.weg.mi75.controller;

import net.weg.mi75.models.dto.ExceptionHandlerResponseDTO;
import net.weg.mi75.models.exceptions.MesmoTitularException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@RestControllerAdvice
//        (basePackageClasses = ContaController.class) // Define para ser apenas para uma classe controller
public class ControllerExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionHandlerResponseDTO capturaDeErro(Exception e) {
        return new ExceptionHandlerResponseDTO(e.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionHandlerResponseDTO capturaDeNaoEncontrado(NoSuchElementException e) {
        return new ExceptionHandlerResponseDTO(e.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(MesmoTitularException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionHandlerResponseDTO capturaDeMesmoTitular(MesmoTitularException e) {
        return new ExceptionHandlerResponseDTO(e.getMessage(), LocalDateTime.now());
    }
}
