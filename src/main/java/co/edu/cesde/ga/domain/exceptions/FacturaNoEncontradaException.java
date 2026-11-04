package co.edu.cesde.ga.domain.exceptions;

public class FacturaNoEncontradaException extends RuntimeException {

    public FacturaNoEncontradaException(String mensaje) {
        super(mensaje);
    }

    public FacturaNoEncontradaException(Long id) {
        super("Factura no encontrada con id: " + id);
    }
}
