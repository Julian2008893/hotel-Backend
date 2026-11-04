package co.edu.cesde.ga.infrastructure.repository;

import co.edu.cesde.ga.domain.models.Hospedaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HospedajeJpaRepository extends JpaRepository<Hospedaje, Long> {

    Optional<Hospedaje> findByClienteIdAndActivoTrue(Long clienteId);

    boolean existsByClienteIdAndActivoTrue(Long clienteId);
}
