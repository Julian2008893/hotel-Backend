package co.edu.cesde.ga.domain.exceptions;

import co.edu.cesde.ga.domain.models.TipoHabitacion;

public class HabitacionNoDisponibleException extends RuntimeException {

    public HabitacionNoDisponibleException(String mensaje) {
        super(mensaje);
    }

    public HabitacionNoDisponibleException(TipoHabitacion tipo) {
        super("No existen habitaciones disponibles para el tipo solicitado: " + tipo);
    }
}
