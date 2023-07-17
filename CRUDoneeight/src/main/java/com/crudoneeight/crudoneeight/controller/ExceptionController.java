package com.crudoneeight.crudoneeight.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.crudoneeight.crudoneeight.model.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        // Obtener información del error
        String errorMessage = ex.getMessage();

        // Crear una instancia de ErrorResponse con los detalles del error
        ErrorResponse errorResponse = new ErrorResponse(errorMessage);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}
