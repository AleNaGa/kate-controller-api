package com.vedruna.kate_controller_api.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * Maneja la excepción IllegalArgumentException y devuelve una respuesta con un error 400.
     *
     * @param ex La excepción IllegalArgumentException
     * @return Una respuesta con un error 400 de ResponseEntity
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgument(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body(Map.of(
                "timestamp", LocalDateTime.now(),
                "error", "Bad request",
                "message", ex.getMessage()
        ));
    }


    /**
     * Maneja la excepción Exception genérica y devuelve una respuesta con un error 500.
     *
     * @param ex La excepción Exception
     * @return Una respuesta con un error 500 de ResponseEntity
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGenericException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                "timestamp", LocalDateTime.now(),
                "error", "Internal server error",
                "message", ex.getMessage()
        ));
    }
}
