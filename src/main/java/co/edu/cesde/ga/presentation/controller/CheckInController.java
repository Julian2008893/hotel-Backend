package co.edu.cesde.ga.presentation.controller;

import co.edu.cesde.ga.application.dto.request.CheckInRequest;
import co.edu.cesde.ga.application.dto.response.CheckInResponse;
import co.edu.cesde.ga.application.repository.CheckInRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/checkin")
@RequiredArgsConstructor
@Tag(name = "Check-In", description = "Registro de ingreso de clientes")
public class CheckInController {

    private final CheckInRepository checkInService;

    @PostMapping
    @Operation(summary = "Realizar Check-In")
    public ResponseEntity<CheckInResponse> realizarCheckIn(@Valid @RequestBody CheckInRequest request) {
        CheckInResponse response = checkInService.realizarCheckIn(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
