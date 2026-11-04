package co.edu.cesde.ga.application.dto.response;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteResponse {

    private Long id;
    private String identificacion;
    private String nombreCompleto;
    private String correoElectronico;
    private String telefono;
    private LocalDateTime fechaCreacion;
}
