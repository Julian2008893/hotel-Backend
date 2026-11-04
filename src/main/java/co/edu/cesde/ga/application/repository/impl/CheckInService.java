package co.edu.cesde.ga.application.repository.impl;

import co.edu.cesde.ga.application.dto.request.CheckInRequest;
import co.edu.cesde.ga.application.dto.response.CheckInResponse;
import co.edu.cesde.ga.application.repository.CheckInRepository;
import co.edu.cesde.ga.domain.exceptions.ClienteConHospedajeActivoException;
import co.edu.cesde.ga.domain.exceptions.ClienteNoEncontradoException;
import co.edu.cesde.ga.domain.exceptions.HabitacionNoDisponibleException;
import co.edu.cesde.ga.domain.models.*;
import co.edu.cesde.ga.infrastructure.repository.ClienteJpaRepository;
import co.edu.cesde.ga.infrastructure.repository.HabitacionJpaRepository;
import co.edu.cesde.ga.infrastructure.repository.HospedajeJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CheckInService implements CheckInRepository {

    private final ClienteJpaRepository clienteJpaRepository;
    private final HabitacionJpaRepository habitacionJpaRepository;
    private final HospedajeJpaRepository hospedajeJpaRepository;

    @Override
    @Transactional
    public CheckInResponse realizarCheckIn(CheckInRequest request) {
        Cliente cliente = clienteJpaRepository.findById(request.getClienteId())
                .orElseThrow(() -> new ClienteNoEncontradoException(request.getClienteId()));

        if (hospedajeJpaRepository.existsByClienteIdAndActivoTrue(cliente.getId())) {
            throw new ClienteConHospedajeActivoException(cliente.getId());
        }

        List<Habitacion> disponibles = habitacionJpaRepository
                .findByTipoHabitacionAndEstado(request.getTipoHabitacion(), EstadoHabitacion.DISPONIBLE);

        if (disponibles.isEmpty()) {
            throw new HabitacionNoDisponibleException(request.getTipoHabitacion());
        }

        Habitacion habitacion = disponibles.get(0);
        habitacion.setEstado(EstadoHabitacion.OCUPADA);
        habitacionJpaRepository.save(habitacion);

        LocalDateTime ahora = LocalDateTime.now();

        Hospedaje hospedaje = Hospedaje.builder()
                .cliente(cliente)
                .habitacion(habitacion)
                .fechaHoraIngreso(ahora)
                .activo(true)
                .build();

        Hospedaje guardado = hospedajeJpaRepository.save(hospedaje);

        return CheckInResponse.builder()
                .hospedajeId(guardado.getId())
                .clienteId(cliente.getId())
                .cliente(cliente.getNombreCompleto())
                .numeroHabitacion(habitacion.getNumeroHabitacion())
                .tipoHabitacion(habitacion.getTipoHabitacion())
                .fechaHoraIngreso(guardado.getFechaHoraIngreso())
                .mensaje("Check-In realizado exitosamente. Habitación asignada: " + habitacion.getNumeroHabitacion())
                .build();
    }
}
