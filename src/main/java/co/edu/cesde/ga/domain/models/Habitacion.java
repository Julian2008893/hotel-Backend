package co.edu.cesde.ga.domain.models;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "habitacion")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Habitacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_habitacion", nullable = false, unique = true)
    private Integer numeroHabitacion;

    @Column(nullable = false)
    private Integer piso;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_habitacion", nullable = false, length = 20)
    private TipoHabitacion tipoHabitacion;

    @Column(name = "valor_noche", nullable = false, precision = 12, scale = 2)
    private BigDecimal valorNoche;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private EstadoHabitacion estado;

    @OneToMany(mappedBy = "habitacion", cascade = CascadeType.ALL, orphanRemoval = false)
    @Builder.Default
    private List<Hospedaje> hospedajes = new ArrayList<>();
}
