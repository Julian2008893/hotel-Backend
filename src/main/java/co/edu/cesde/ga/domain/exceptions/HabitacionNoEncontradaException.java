package co.edu.cesde.ga.domain.exceptions;

public class HabitacionNoEncontradaException extends RuntimeException {

    public HabitacionNoEncontradaException(String mensaje) {
        super(mensaje);
    }

    public HabitacionNoEncontradaException(Integer numero) {
        super("Habitación no encontrada con número: " + numero);
    }
}
