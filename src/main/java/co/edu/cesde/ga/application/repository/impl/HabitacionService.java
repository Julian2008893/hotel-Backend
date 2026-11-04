package co.edu.cesde.ga.application.repository.impl;

import co.edu.cesde.ga.application.dto.response.HabitacionResponse;
import co.edu.cesde.ga.application.repository.HabitacionRepository;
import co.edu.cesde.ga.domain.exceptions.HabitacionNoEncontradaException;
import co.edu.cesde.ga.domain.models.EstadoHabitacion;
import co.edu.cesde.ga.domain.models.Habitacion;
import co.edu.cesde.ga.domain.models.TipoHabitacion;
import co.edu.cesde.ga.infrastructure.repository.HabitacionJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HabitacionService implements HabitacionRepository {

    private final HabitacionJpaRepository habitacionJpaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<HabitacionResponse> consultarTodas() {
        return habitacionJpaRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public HabitacionResponse consultarPorNumero(Integer numero) {
        Habitacion habitacion = habitacionJpaRepository.findByNumeroHabitacion(numero)
                .orElseThrow(() -> new HabitacionNoEncontradaException(numero));
        return mapToResponse(habitacion);
    }

    @Override
    @Transactional(readOnly = true)
    public List<HabitacionResponse> buscarDisponiblePorTipo(TipoHabitacion tipo) {
        return habitacionJpaRepository.findByTipoHabitacionAndEstado(tipo, EstadoHabitacion.DISPONIBLE)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private HabitacionResponse mapToResponse(Habitacion habitacion) {
        return HabitacionResponse.builder()
                .id(habitacion.getId())
                .numeroHabitacion(habitacion.getNumeroHabitacion())
                .piso(habitacion.getPiso())
                .tipoHabitacion(habitacion.getTipoHabitacion())
                .valorNoche(habitacion.getValorNoche())
                .estado(habitacion.getEstado())
                .build();
    }
}
