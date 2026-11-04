package co.edu.cesde.ga.application.dto.response;

import co.edu.cesde.ga.domain.models.EstadoHabitacion;
import co.edu.cesde.ga.domain.models.TipoHabitacion;
import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HabitacionResponse {

    private Long id;
    private Integer numeroHabitacion;
    private Integer piso;
    private TipoHabitacion tipoHabitacion;
    private BigDecimal valorNoche;
    private EstadoHabitacion estado;
}
