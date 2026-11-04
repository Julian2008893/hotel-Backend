package co.edu.cesde.ga.application.dto.response;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorResponse {

    private int codigoHttp;
    private String error;
    private String mensaje;
    private LocalDateTime fechaHora;
}
