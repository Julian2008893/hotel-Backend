package co.edu.cesde.ga.application.repository.impl;

import co.edu.cesde.ga.application.dto.request.ClienteRequest;
import co.edu.cesde.ga.application.dto.response.ClienteResponse;
import co.edu.cesde.ga.application.repository.ClienteRepository;
import co.edu.cesde.ga.domain.exceptions.CheckOutInvalidoException;
import co.edu.cesde.ga.domain.exceptions.ClienteNoEncontradoException;
import co.edu.cesde.ga.domain.models.Cliente;
import co.edu.cesde.ga.infrastructure.repository.ClienteJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClienteService implements ClienteRepository {

    private final ClienteJpaRepository clienteJpaRepository;

    @Override
    @Transactional
    public ClienteResponse crearCliente(ClienteRequest request) {
        if (clienteJpaRepository.existsByIdentificacion(request.getIdentificacion())) {
            throw new CheckOutInvalidoException("Ya existe un cliente con la identificación: " + request.getIdentificacion());
        }

        Cliente cliente = Cliente.builder()
                .identificacion(request.getIdentificacion())
                .nombreCompleto(request.getNombreCompleto())
                .correoElectronico(request.getCorreoElectronico())
                .telefono(request.getTelefono())
                .build();

        Cliente guardado = clienteJpaRepository.save(cliente);
        return mapToResponse(guardado);
    }

    @Override
    @Transactional(readOnly = true)
    public ClienteResponse consultarPorId(Long id) {
        Cliente cliente = clienteJpaRepository.findById(id)
                .orElseThrow(() -> new ClienteNoEncontradoException(id));
        return mapToResponse(cliente);
    }

    @Override
    @Transactional(readOnly = true)
    public ClienteResponse consultarPorIdentificacion(String identificacion) {
        Cliente cliente = clienteJpaRepository.findByIdentificacion(identificacion)
                .orElseThrow(() -> new ClienteNoEncontradoException("identificación", identificacion));
        return mapToResponse(cliente);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClienteResponse> consultarTodos() {
        return clienteJpaRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private ClienteResponse mapToResponse(Cliente cliente) {
        return ClienteResponse.builder()
                .id(cliente.getId())
                .identificacion(cliente.getIdentificacion())
                .nombreCompleto(cliente.getNombreCompleto())
                .correoElectronico(cliente.getCorreoElectronico())
                .telefono(cliente.getTelefono())
                .fechaCreacion(cliente.getFechaCreacion())
                .build();
    }
}
