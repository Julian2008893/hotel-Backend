package co.edu.cesde.ga.presentation.advice;

import co.edu.cesde.ga.application.dto.response.ErrorResponse;
import co.edu.cesde.ga.domain.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ClienteNoEncontradoException.class)
    public ResponseEntity<ErrorResponse> handleClienteNoEncontrado(ClienteNoEncontradoException ex) {
        return buildError(HttpStatus.NOT_FOUND, "Cliente No Encontrado", ex.getMessage());
    }

    @ExceptionHandler(HabitacionNoEncontradaException.class)
    public ResponseEntity<ErrorResponse> handleHabitacionNoEncontrada(HabitacionNoEncontradaException ex) {
        return buildError(HttpStatus.NOT_FOUND, "Habitación No Encontrada", ex.getMessage());
    }

    @ExceptionHandler(FacturaNoEncontradaException.class)
    public ResponseEntity<ErrorResponse> handleFacturaNoEncontrada(FacturaNoEncontradaException ex) {
        return buildError(HttpStatus.NOT_FOUND, "Factura No Encontrada", ex.getMessage());
    }

    @ExceptionHandler(HospedajeActivoNoEncontradoException.class)
    public ResponseEntity<ErrorResponse> handleHospedajeActivoNoEncontrado(HospedajeActivoNoEncontradoException ex) {
        return buildError(HttpStatus.NOT_FOUND, "Hospedaje Activo No Encontrado", ex.getMessage());
    }

    @ExceptionHandler(ClienteConHospedajeActivoException.class)
    public ResponseEntity<ErrorResponse> handleClienteConHospedajeActivo(ClienteConHospedajeActivoException ex) {
        return buildError(HttpStatus.CONFLICT, "Conflicto de Negocio", ex.getMessage());
    }

    @ExceptionHandler(HabitacionNoDisponibleException.class)
    public ResponseEntity<ErrorResponse> handleHabitacionNoDisponible(HabitacionNoDisponibleException ex) {
        return buildError(HttpStatus.CONFLICT, "Conflicto de Negocio", ex.getMessage());
    }

    @ExceptionHandler(CheckOutInvalidoException.class)
    public ResponseEntity<ErrorResponse> handleCheckOutInvalido(CheckOutInvalidoException ex) {
        return buildError(HttpStatus.BAD_REQUEST, "Check-Out Inválido", ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex) {
        String mensaje = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining("; "));
        return buildError(HttpStatus.BAD_REQUEST, "Solicitud Inválida", mensaje);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneral(Exception ex) {
        return buildError(HttpStatus.INTERNAL_SERVER_ERROR, "Error Interno del Servidor",
                ex.getMessage() != null ? ex.getMessage() : "Ocurrió un error inesperado");
    }

    private ResponseEntity<ErrorResponse> buildError(HttpStatus status, String error, String mensaje) {
        ErrorResponse body = ErrorResponse.builder()
                .codigoHttp(status.value())
                .error(error)
                .mensaje(mensaje)
                .fechaHora(LocalDateTime.now())
                .build();
        return ResponseEntity.status(status).body(body);
    }
}
