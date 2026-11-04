package co.edu.cesde.ga.application.dto.response;

import co.edu.cesde.ga.domain.models.TipoHabitacion;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FacturaResponse {

    private Long facturaId;
    private Long clienteId;
    private String cliente;
    private Integer numeroHabitacion;
    private TipoHabitacion tipoHabitacion;
    private LocalDateTime fechaHoraIngreso;
    private LocalDateTime fechaHoraSalida;
    private Integer cantidadNoches;
    private BigDecimal valorNoche;
    private BigDecimal valorTotal;
    private LocalDateTime fechaGeneracion;
}
