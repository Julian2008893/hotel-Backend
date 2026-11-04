package co.edu.cesde.ga.domain.exceptions;

public class HospedajeActivoNoEncontradoException extends RuntimeException {

    public HospedajeActivoNoEncontradoException(String mensaje) {
        super(mensaje);
    }

    public HospedajeActivoNoEncontradoException(Long clienteId) {
        super("No se encontró un hospedaje activo para el cliente con id: " + clienteId);
    }
}
