package co.edu.cesde.ga.presentation.controller;

import co.edu.cesde.ga.application.dto.response.FacturaResponse;
import co.edu.cesde.ga.application.repository.FacturaRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/facturas")
@RequiredArgsConstructor
@Tag(name = "Facturas", description = "Consulta de facturas generadas")
public class FacturaController {

    private final FacturaRepository facturaService;

    @GetMapping
    @Operation(summary = "Consultar todas las facturas")
    public ResponseEntity<List<FacturaResponse>> consultarTodas() {
        return ResponseEntity.ok(facturaService.consultarTodas());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consultar factura por ID")
    public ResponseEntity<FacturaResponse> consultarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(facturaService.consultarPorId(id));
    }
}
