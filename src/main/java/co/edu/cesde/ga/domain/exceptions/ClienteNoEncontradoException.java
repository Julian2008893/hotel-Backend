package co.edu.cesde.ga.domain.exceptions;

public class ClienteNoEncontradoException extends RuntimeException {

    public ClienteNoEncontradoException(String mensaje) {
        super(mensaje);
    }

    public ClienteNoEncontradoException(Long id) {
        super("Cliente no encontrado con id: " + id);
    }

    public ClienteNoEncontradoException(String campo, String valor) {
        super("Cliente no encontrado con " + campo + ": " + valor);
    }
}
