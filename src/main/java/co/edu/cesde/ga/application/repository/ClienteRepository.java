package co.edu.cesde.ga.application.repository;

import co.edu.cesde.ga.application.dto.request.ClienteRequest;
import co.edu.cesde.ga.application.dto.response.ClienteResponse;

import java.util.List;

public interface ClienteRepository {

    ClienteResponse crearCliente(ClienteRequest request);

    ClienteResponse consultarPorId(Long id);

    ClienteResponse consultarPorIdentificacion(String identificacion);

    List<ClienteResponse> consultarTodos();
}
