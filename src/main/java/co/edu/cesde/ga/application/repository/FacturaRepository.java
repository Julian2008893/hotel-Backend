package co.edu.cesde.ga.application.repository;

import co.edu.cesde.ga.application.dto.response.FacturaResponse;

import java.util.List;

public interface FacturaRepository {

    FacturaResponse consultarPorId(Long id);

    List<FacturaResponse> consultarTodas();
}
