package co.edu.cesde.ga.application.repository.impl;

import co.edu.cesde.ga.application.dto.request.CheckOutRequest;
import co.edu.cesde.ga.application.dto.response.FacturaResponse;
import co.edu.cesde.ga.application.repository.CheckOutRepository;
import co.edu.cesde.ga.domain.exceptions.ClienteNoEncontradoException;
import co.edu.cesde.ga.domain.exceptions.HospedajeActivoNoEncontradoException;
import co.edu.cesde.ga.domain.models.*;
import co.edu.cesde.ga.infrastructure.repository.ClienteJpaRepository;
import co.edu.cesde.ga.infrastructure.repository.FacturaJpaRepository;
import co.edu.cesde.ga.infrastructure.repository.HabitacionJpaRepository;
import co.edu.cesde.ga.infrastructure.repository.HospedajeJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class CheckOutService implements CheckOutRepository {

    private static final LocalTime HORA_CHECK_OUT = LocalTime.of(13, 0);

    private final ClienteJpaRepository clienteJpaRepository;
    private final HospedajeJpaRepository hospedajeJpaRepository;
    private final HabitacionJpaRepository habitacionJpaRepository;
    private final FacturaJpaRepository facturaJpaRepository;

    @Override
    @Transactional
    public FacturaResponse realizarCheckOut(CheckOutRequest request) {
        Cliente cliente = clienteJpaRepository.findById(request.getClienteId())
                .orElseThrow(() -> new ClienteNoEncontradoException(request.getClienteId()));

        Hospedaje hospedaje = hospedajeJpaRepository.findByClienteIdAndActivoTrue(cliente.getId())
                .orElseThrow(() -> new HospedajeActivoNoEncontradoException(cliente.getId()));

        LocalDateTime fechaHoraSalida = LocalDateTime.now();
        hospedaje.setFechaHoraSalida(fechaHoraSalida);
        hospedaje.setActivo(false);

        Habitacion habitacion = hospedaje.getHabitacion();
        habitacion.setEstado(EstadoHabitacion.DISPONIBLE);
        habitacionJpaRepository.save(habitacion);

        int cantidadNoches = calcularNoches(hospedaje.getFechaHoraIngreso(), fechaHoraSalida);
        BigDecimal valorNoche = habitacion.getValorNoche();
        BigDecimal valorTotal = valorNoche.multiply(BigDecimal.valueOf(cantidadNoches));

        Factura factura = Factura.builder()
                .hospedaje(hospedaje)
                .cantidadNoches(cantidadNoches)
                .valorNoche(valorNoche)
                .valorTotal(valorTotal)
                .build();

        Factura facturaGuardada = facturaJpaRepository.save(factura);
        hospedaje.setFactura(facturaGuardada);
        hospedajeJpaRepository.save(hospedaje);

        return FacturaResponse.builder()
                .facturaId(facturaGuardada.getId())
                .clienteId(cliente.getId())
                .cliente(cliente.getNombreCompleto())
                .numeroHabitacion(habitacion.getNumeroHabitacion())
                .tipoHabitacion(habitacion.getTipoHabitacion())
                .fechaHoraIngreso(hospedaje.getFechaHoraIngreso())
                .fechaHoraSalida(fechaHoraSalida)
                .cantidadNoches(cantidadNoches)
                .valorNoche(valorNoche)
                .valorTotal(valorTotal)
                .fechaGeneracion(facturaGuardada.getFechaGeneracion())
                .build();
    }

    /**
     * Calcula noches según reglas de negocio:
     * - Mínimo 1 noche.
     * - Si la salida es después de las 13:00, se factura una noche adicional.
     */
    private int calcularNoches(LocalDateTime ingreso, LocalDateTime salida) {
        LocalDate fechaIngreso = ingreso.toLocalDate();
        LocalDate fechaSalida = salida.toLocalDate();

        long diasEntre = ChronoUnit.DAYS.between(fechaIngreso, fechaSalida);

        int noches;

        if (diasEntre == 0) {
            noches = 1;
        } else {
            noches = (int) diasEntre;
            if (salida.toLocalTime().isAfter(HORA_CHECK_OUT)) {
                noches += 1;
            }
        }

        return Math.max(noches, 1);
    }
}
