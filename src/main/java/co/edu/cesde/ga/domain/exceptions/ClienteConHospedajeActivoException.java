package co.edu.cesde.ga.domain.exceptions;

public class ClienteConHospedajeActivoException extends RuntimeException {

    public ClienteConHospedajeActivoException(String mensaje) {
        super(mensaje);
    }

    public ClienteConHospedajeActivoException(Long clienteId) {
        super("El cliente con id " + clienteId + " ya tiene un hospedaje activo");
    }
}
