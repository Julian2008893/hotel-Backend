package co.edu.cesde.ga.application.dto.response;

import co.edu.cesde.ga.domain.models.TipoHabitacion;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CheckInResponse {

    private Long hospedajeId;
    private Long clienteId;
    private String cliente;
    private Integer numeroHabitacion;
    private TipoHabitacion tipoHabitacion;
    private LocalDateTime fechaHoraIngreso;
    private String mensaje;
}
