package co.edu.cesde.ga.presentation.controller;

import co.edu.cesde.ga.application.dto.request.CheckOutRequest;
import co.edu.cesde.ga.application.dto.response.FacturaResponse;
import co.edu.cesde.ga.application.repository.CheckOutRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/checkout")
@RequiredArgsConstructor
@Tag(name = "Check-Out", description = "Registro de salida y generación de factura")
public class CheckOutController {

    private final CheckOutRepository checkOutService;

    @PostMapping
    @Operation(summary = "Realizar Check-Out")
    public ResponseEntity<FacturaResponse> realizarCheckOut(@Valid @RequestBody CheckOutRequest request) {
        FacturaResponse response = checkOutService.realizarCheckOut(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
