package co.edu.cesde.ga.application.repository;

import co.edu.cesde.ga.application.dto.response.HabitacionResponse;
import co.edu.cesde.ga.domain.models.TipoHabitacion;

import java.util.List;

public interface HabitacionRepository {

    List<HabitacionResponse> consultarTodas();

    HabitacionResponse consultarPorNumero(Integer numero);

    List<HabitacionResponse> buscarDisponiblePorTipo(TipoHabitacion tipo);
}
