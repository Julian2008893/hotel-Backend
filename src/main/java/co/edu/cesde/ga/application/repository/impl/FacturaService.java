package co.edu.cesde.ga.application.repository.impl;

import co.edu.cesde.ga.application.dto.response.FacturaResponse;
import co.edu.cesde.ga.application.repository.FacturaRepository;
import co.edu.cesde.ga.domain.exceptions.FacturaNoEncontradaException;
import co.edu.cesde.ga.domain.models.Factura;
import co.edu.cesde.ga.domain.models.Hospedaje;
import co.edu.cesde.ga.infrastructure.repository.FacturaJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FacturaService implements FacturaRepository {

    private final FacturaJpaRepository facturaJpaRepository;

    @Override
    @Transactional(readOnly = true)
    public FacturaResponse consultarPorId(Long id) {
        Factura factura = facturaJpaRepository.findById(id)
                .orElseThrow(() -> new FacturaNoEncontradaException(id));
        return mapToResponse(factura);
    }

    @Override
    @Transactional(readOnly = true)
    public List<FacturaResponse> consultarTodas() {
        return facturaJpaRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private FacturaResponse mapToResponse(Factura factura) {
        Hospedaje hospedaje = factura.getHospedaje();
        return FacturaResponse.builder()
                .facturaId(factura.getId())
                .clienteId(hospedaje.getCliente().getId())
                .cliente(hospedaje.getCliente().getNombreCompleto())
                .numeroHabitacion(hospedaje.getHabitacion().getNumeroHabitacion())
                .tipoHabitacion(hospedaje.getHabitacion().getTipoHabitacion())
                .fechaHoraIngreso(hospedaje.getFechaHoraIngreso())
                .fechaHoraSalida(hospedaje.getFechaHoraSalida())
                .cantidadNoches(factura.getCantidadNoches())
                .valorNoche(factura.getValorNoche())
                .valorTotal(factura.getValorTotal())
                .fechaGeneracion(factura.getFechaGeneracion())
                .build();
    }
}
