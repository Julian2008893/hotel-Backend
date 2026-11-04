package co.edu.cesde.ga.application.dto.request;

import co.edu.cesde.ga.domain.models.TipoHabitacion;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CheckInRequest {

    @NotNull(message = "El id del cliente es obligatorio")
    private Long clienteId;

    @NotNull(message = "El tipo de habitación es obligatorio")
    private TipoHabitacion tipoHabitacion;
}
